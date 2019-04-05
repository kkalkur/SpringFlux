package com.wirebraincoffe.productapiannotation.validator;

import com.wirebraincoffe.productapiannotation.exception.InvalidParamterException;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;

public class StringValidator implements validate {

    private String key;

    public StringValidator(String key) {
        this.key = key;
    }

    @Override
    public void validate(Map<String, String> customQuery) throws InvalidParamterException {

        String parametrvalue = customQuery.get(key);
            if(parametrvalue==null){
                throw new InvalidParamterException(key+"cannot be null");
            }

            if(StringUtils.isEmpty(parametrvalue)){
                throw new InvalidParamterException(key+"cannot be empty");
            }



    }
}
