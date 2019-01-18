package com.epam.brest.cources;

import java.math.BigDecimal;

public class DeliveryCost {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        double a = 0.02;
        double b = 0.03;
        double c = b - a;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("b - a = " + c);

        BigDecimal aaa = new BigDecimal("0.02");
        BigDecimal bbb = new BigDecimal("0.03");
        BigDecimal ccc = bbb.subtract(aaa);
        System.out.println("bbb - aaa = " + ccc);
    }
}
