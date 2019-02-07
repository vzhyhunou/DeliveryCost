package com.epam.brest.cources.calc;

import com.epam.brest.cources.files.DataWriter;
import com.epam.brest.cources.files.JSONWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class SerializationTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void test() throws Exception {
        DataWriter writer = new JSONWriter();
        byte[] bytes = writer.marshal(create(), create());
        LOGGER.info("Data: {}", new String(bytes));
        Object[] result = writer.unmarshal(bytes);
        LOGGER.info("Result: {}", Arrays.toString(result));
    }

    private DataItem create() {

        DataItem dataItem = new DataItem();
        dataItem.setWeight(new BigDecimal("1"));
        dataItem.setDistance(new BigDecimal("2"));
        dataItem.setPricePerKg(new BigDecimal("3"));
        dataItem.setPricePerKm(new BigDecimal("4"));

        return dataItem;
    }

}