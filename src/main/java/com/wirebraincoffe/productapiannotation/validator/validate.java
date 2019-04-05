package com.wirebraincoffe.productapiannotation.validator;

import com.wirebraincoffe.productapiannotation.exception.InvalidParamterException;

import java.util.Map;


public interface validate {

     void validate(Map<String,String> customQuery) throws InvalidParamterException;
}


