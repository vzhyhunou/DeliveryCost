package com.epam.brest.cources.calc;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    @Override
    public BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

    @Override
    public BigDecimal calc(DataItem dataItem) {
        return dataItem.getWeight().multiply(dataItem.getPricePerKg()).add(dataItem.getDistance().multiply(dataItem.getPricePerKm()));
    }
}
