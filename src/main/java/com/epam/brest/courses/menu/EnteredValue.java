package com.epam.brest.courses.menu;

public interface EnteredValue {
    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}
