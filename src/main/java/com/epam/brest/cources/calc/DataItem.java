package com.epam.brest.cources.calc;

import java.math.BigDecimal;

public class DataItem {

    BigDecimal weight;

    BigDecimal distance;

    BigDecimal pricePerKg;

    BigDecimal pricePerKm;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", pricePerKg=" + pricePerKg +
                ", pricePerKm=" + pricePerKm +
                '}';
    }
}
