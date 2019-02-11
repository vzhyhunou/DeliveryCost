package com.epam.brest.courses.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectWriter implements DataWriter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public byte[] marshal(Object... objects) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(stream);
        for (Object obj : objects) {
            out.writeObject(obj);
        }
        out.close();
        return stream.toByteArray();
    }

    @Override
    public Object[] unmarshal(byte[] bytes) throws Exception {
        List<Object> list = new ArrayList<>();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
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
