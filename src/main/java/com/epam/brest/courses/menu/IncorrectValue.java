package com.epam.brest.courses.menu;

public class IncorrectValue implements EnteredValue {

    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
