package com.epam.brest.cources.files;

import com.epam.brest.cources.calc.DataItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONWriter implements DataWriter {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public byte[] marshal(Object... objects) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (Object obj : objects) {
            MAPPER.writeValue(stream, obj);
        }

        return stream.toByteArray();
    }

    @Override
    public Object[] unmarshal(byte[] bytes) throws Exception {
        List<Object> list = new ArrayList<>();
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            while (true) {
                list.add(MAPPER.readValue(in, DataItem.class));
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }
        return list.toArray();
    }
}
