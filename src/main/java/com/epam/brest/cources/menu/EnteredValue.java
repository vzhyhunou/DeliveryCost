package com.epam.brest.cources.menu;

public interface EnteredValue {
    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}
