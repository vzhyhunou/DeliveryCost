package com.epam.brest.cources.calc;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

class CalculatorImplTest {

    Calculator calculator = new CalculatorImpl();

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach - executes before each test method in this class");
    }

    @DisplayName("Single test successful")
    @Test
    void calc() {
        BigDecimal result = calculator.calc(new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("4"));
        Assertions.assertEquals(new BigDecimal("11"), result);
    }

}