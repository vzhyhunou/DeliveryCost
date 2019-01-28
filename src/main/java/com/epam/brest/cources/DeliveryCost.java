package com.epam.brest.cources;

import com.epam.brest.cources.calc.CalculatorImpl;
import com.epam.brest.cources.calc.DataItem;
import com.epam.brest.cources.files.FileReader;
import com.epam.brest.cources.menu.CorrectValue;
import com.epam.brest.cources.menu.EnteredValue;
import com.epam.brest.cources.menu.EnteredValue.Types;
import com.epam.brest.cources.menu.ExitValue;
import com.epam.brest.cources.menu.IncorrectValue;
import com.epam.brest.cources.files.CSVFileReader;
import com.epam.brest.cources.selector.PriceSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;


public class DeliveryCost {

    private static final String PREFIX = "Enter ";
    //private static final String[] MESSAGES = {"weight of cargo in kg", "distance in km", "price per kg", "price per km"};
    private static final String[] MESSAGES = {"weight of cargo in kg", "distance in km"};
    private static final String POSTFIX = " or 'q' for quit";
    private static final BigDecimal[] ENTERED_VALUES = new BigDecimal[2];

    private static final String QUIT_SYMBOL = "q";

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

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


        //Fill array by values
        EnteredValue ew = null;
        for (int i = 0; i < MESSAGES.length; i++) {
            ew = deliveryCost.receiveValueFromConsole(PREFIX + MESSAGES[i] + POSTFIX, scanner);
            switch (ew.getType()) {
                case VALUE:
                    ENTERED_VALUES[i] = ((CorrectValue) ew).getValue();
                    break;
                default:
                    i = MESSAGES.length;
            }
        }

        PriceSelector selector = new PriceSelector();

        //Calculate result
        if (ew != null && ew.getType() != Types.EXIT) {
            DataItem dataItem = new DataItem();
            dataItem.setWeight(ENTERED_VALUES[0]);
            dataItem.setDistance(ENTERED_VALUES[1]);
            dataItem.setPricePerKg(selector.selectPriceValue(kgs, dataItem.getWeight()));
            dataItem.setPricePerKm(selector.selectPriceValue(kms, dataItem.getDistance()));

            BigDecimal calcResult = new CalculatorImpl().calc(dataItem);
            LOGGER.info("Data item: {}", dataItem);
            LOGGER.info("Delivery cost: {} {}", dataItem, calcResult);
        }

        System.out.println("Bye!");
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
