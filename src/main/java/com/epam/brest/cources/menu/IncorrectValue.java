package com.epam.brest.cources.menu;

public class IncorrectValue implements EnteredValue {

    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
