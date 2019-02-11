package com.epam.brest.courses.calc;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm);

    BigDecimal calc(DataItem dataItem);

}
