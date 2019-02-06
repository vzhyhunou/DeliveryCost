package com.epam.brest.cources.calc;

import com.epam.brest.cources.files.DataWriter;
import com.epam.brest.cources.files.JSONWriter;
import com.epam.brest.cources.files.ObjectWriter;
import com.epam.brest.cources.files.XMLWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SerializationTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Parameterized.Parameter
    public DataWriter writer;

    @Test
    public void test() throws Exception {
        byte[] data = writer.marshal(create(), create());
        LOGGER.info("Data: {}", new String(data));
        Object[] result = writer.unmarshal(data);
        LOGGER.info("Result: {}", Arrays.toString(result));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {new ObjectWriter()},
                {new XMLWriter()},
                {new JSONWriter()}
        };
        return Arrays.asList(data);
    }

    private DataItem create() {
        DataItem dataItem = new DataItem();
        dataItem.setWeight(new BigDecimal("21"));
        dataItem.setDistance(new BigDecimal("22"));
        dataItem.setPricePerKg(new BigDecimal("23"));
        dataItem.setPricePerKm(new BigDecimal("24"));
        return dataItem;
    }
}
