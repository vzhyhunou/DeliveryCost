package com.epam.brest.cources;

import com.epam.brest.cources.calc.CalculatorImpl;
import com.epam.brest.cources.calc.DataItem;
import com.epam.brest.cources.menu.CorrectValue;
import com.epam.brest.cources.menu.EnteredValue;
import com.epam.brest.cources.menu.EnteredValue.Types;
import com.epam.brest.cources.menu.ExitValue;
import com.epam.brest.cources.menu.IncorrectValue;
import com.epam.brest.cources.utils.CSVFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class DeliveryCost {

    private static final String PREFIX = "Enter ";
    private static final String[] MESSAGES = {"weight of cargo in kg", "distance in km", "price per kg", "price per km"};
    private static final String POSTFIX = " or 'q' for quit";
    private static final BigDecimal[] ENTERED_VALUES = new BigDecimal[4];

    private static final String QUIT_SYMBOL = "q";

    public static void main(String[] args) throws IOException {

        DeliveryCost deliveryCost = new DeliveryCost();
        Scanner scanner = new Scanner(System.in);

        Map<String, String> kgs = CSVFile.getMapFromCSV("price_kg.csv");
        if ((kgs != null) && (!kgs.isEmpty())) {

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

        //Calculate result
        if (ew != null && ew.getType() != Types.EXIT) {
            DataItem dataItem = new DataItem();
            dataItem.setWeight(ENTERED_VALUES[0]);
            dataItem.setDistance(ENTERED_VALUES[1]);
            dataItem.setPricePerKg(ENTERED_VALUES[2]);
            dataItem.setPricePerKm(ENTERED_VALUES[3]);

            BigDecimal calcResult = new CalculatorImpl().calc(dataItem);

            System.out.println(dataItem);
            System.out.format("Delivery cost = %.2f$%n", calcResult);
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
