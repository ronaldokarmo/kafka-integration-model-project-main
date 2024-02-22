package com.demo.support;

import io.cucumber.java.ParameterType;

public class TestSupport {

    @ParameterType("true|false")
    public Boolean booleanValue(String booleanString) {
        return Boolean.valueOf(booleanString);
    }

}
