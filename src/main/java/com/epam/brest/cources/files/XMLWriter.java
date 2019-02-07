package com.epam.brest.cources.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLWriter implements DataWriter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public byte[] marshal(Object... objects) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        XMLEncoder out = new XMLEncoder(stream);
        for (Object obj : objects) {
            out.writeObject(obj);
        }
        out.close();
        return stream.toByteArray();
    }

    @Override
    public Object[] unmarshal(byte[] bytes) throws Exception {
        List<Object> list = new ArrayList<>();

        XMLDecoder in = new XMLDecoder(new ByteArrayInputStream(bytes));
        try {
            while (true) {
                list.add(in.readObject());
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }
        return list.toArray();
    }
}
