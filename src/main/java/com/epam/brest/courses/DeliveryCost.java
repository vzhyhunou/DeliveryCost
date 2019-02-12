package com.epam.brest.courses;

import com.epam.brest.courses.calc.Calculator;
import com.epam.brest.courses.calc.DataItem;
import com.epam.brest.courses.files.CSVFileReader;
import com.epam.brest.courses.files.FileReader;
import com.epam.brest.courses.menu.CorrectValue;
import com.epam.brest.courses.menu.EnteredValue;
import com.epam.brest.courses.menu.EnteredValue.Types;
import com.epam.brest.courses.menu.ExitValue;
import com.epam.brest.courses.menu.IncorrectValue;
import com.epam.brest.courses.selector.PriceSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;


public class DeliveryCost {

    private static final String QUIT_SYMBOL = "q";

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("messages.xml", "calc.xml");
        Calculator calculator = (Calculator) applicationContext.getBean("calc");
        Properties messages = (Properties) applicationContext.getBean("appMessages");

        DeliveryCost deliveryCost = new DeliveryCost();
        Scanner scanner = new Scanner(System.in);

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> kgs = fileReader.readData("price_kg.csv");
        if (kgs == null || kgs.isEmpty()) {
            throw new FileNotFoundException("File with prices per kg not found.");
        }

        Map<Integer, BigDecimal> kms = fileReader.readData("price_km.csv");
        if (kms == null || kms.isEmpty()) {
            throw new FileNotFoundException("File with prices per km not found.");
        }

        EnteredValue weightValue =
                deliveryCost.receiveValueFromConsole(messages.getProperty("weight.message"), scanner);
        if (deliveryCost.notNeededExit(weightValue)) {
            EnteredValue distanceValue = deliveryCost.receiveValueFromConsole(messages.getProperty("distance.message"), scanner);
            if (deliveryCost.notNeededExit(distanceValue)) {
                PriceSelector selector = new PriceSelector();

                DataItem dataItem = new DataItem();
                dataItem.setWeight(((CorrectValue) weightValue).getValue());
                dataItem.setDistance(((CorrectValue) distanceValue).getValue());
                dataItem.setPricePerKg(selector.selectPriceValue(kgs, dataItem.getWeight()));
                dataItem.setPricePerKm(selector.selectPriceValue(kms, dataItem.getDistance()));

                BigDecimal calcResult = calculator.calc(dataItem);
                LOGGER.info("Data item: {}", dataItem);
                LOGGER.info("Delivery cost: {} {}", dataItem, calcResult);
            }
        }

        System.out.println("Bye!");
    }

    private boolean notNeededExit(EnteredValue enteredValue) {
        return enteredValue != null && enteredValue.getType() != Types.EXIT;
    }

    private EnteredValue receiveValueFromConsole(String message, Scanner scanner) {
        EnteredValue result = new IncorrectValue();
        while (result.getType() == Types.INCORRECT) {
            System.out.println(message);
            result = parseInputValue(scanner.nextLine());
        }
        return result;
    }

    private EnteredValue parseInputValue(String inputValue) {
        EnteredValue result = new ExitValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }
        }
        return result;
    }
}
