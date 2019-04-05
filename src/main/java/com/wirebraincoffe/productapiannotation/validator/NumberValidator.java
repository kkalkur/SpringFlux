package com.wirebraincoffe.productapiannotation.validator;

import com.wirebraincoffe.productapiannotation.exception.InvalidParamterException;

import java.util.Map;

public class NumberValidator implements validate {

    private String key;

    public NumberValidator(String key) {
        this.key = key;
    }

    @Override
    public void validate(Map<String, String> customQuery) throws InvalidParamterException {

        String parametrvalue = customQuery.get(key);
        try {
            if(parametrvalue==null){
                throw new InvalidParamterException(key+"cannot be null");
            }
            int value = Integer.valueOf(parametrvalue);
            if(value<0){
                throw new InvalidParamterException("The"+key+"Should be more than zero");
            } else if(value>255){
                throw new InvalidParamterException("The"+key+"Should be less than 255");
            }

        } catch(NumberFormatException exp){
            throw new InvalidParamterException("The"+key+"Not a valid");
        }



    }
}
