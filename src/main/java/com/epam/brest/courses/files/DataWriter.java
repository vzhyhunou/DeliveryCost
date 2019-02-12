package com.epam.brest.courses.files;

public interface DataWriter {

    byte[] marshal(Object... objects) throws Exception;

    Object[] unmarshal(byte[] bytes) throws Exception;
}
