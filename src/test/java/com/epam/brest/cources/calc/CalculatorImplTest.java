package com.epam.brest.cources.calc;

import org.opentest4j.AssertionFailedError;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

class CalculatorImplTest {

    Calculator calculator = new CalculatorImpl();

    DataItem dataItem;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach");

        dataItem = new DataItem();
        dataItem.setWeight(new BigDecimal("1"));
        dataItem.setDistance(new BigDecimal("2"));
        dataItem.setPricePerKg(new BigDecimal("3"));
        dataItem.setPricePerKm(new BigDecimal("4"));
    }

    @Test
    void calc() {
        System.out.println("@Test 1");

        BigDecimal calcResult = calculator.calc(dataItem);
        Assertions.assertEquals(new BigDecimal("11"), calcResult);
    }

    @Test
    void incorrectTest() {
        System.out.println("@Test 2");

        BigDecimal calcResult = calculator.calc(dataItem);

        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertEquals(new BigDecimal("12"), calcResult);
        });
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }


}