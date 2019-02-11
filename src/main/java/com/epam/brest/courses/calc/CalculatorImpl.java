package com.epam.brest.courses.calc;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    @Override
    public BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

    @Override
    public BigDecimal calc(DataItem dataItem) {
        return calc(dataItem.getWeight(), dataItem.getDistance(), dataItem.getPricePerKg(), dataItem.getPricePerKm());
    }
}
