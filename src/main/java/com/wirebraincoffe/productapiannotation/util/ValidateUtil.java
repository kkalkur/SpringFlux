package com.wirebraincoffe.productapiannotation.util;

import com.wirebraincoffe.productapiannotation.validator.NumberValidator;
import com.wirebraincoffe.productapiannotation.validator.StringValidator;
import com.wirebraincoffe.productapiannotation.validator.validate;
import com.wirebraincoffe.productapiannotation.vo.enrollementQuery;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ValidateUtil {




    public static Mono<enrollementQuery> validatePatemeter(Map<String,String> customQuery){
        Map<String, validate> validators = new HashMap<>();
        validators.put("program",new StringValidator("program"));
        validators.put("limit",new NumberValidator("limit"));
        validators.put("provider",new StringValidator("provider"));
        Mono<String> validationResults = Mono.just("results");
    return   validationResults.map((input)-> {
        validators.forEach(
               (k,v) ->
                   v.validate(customQuery));
            return convertToVo(customQuery);

    });


    }

    public static enrollementQuery convertToVo(Map<String,String> customQuery) {
        enrollementQuery inputVo = new enrollementQuery();
        customQuery.forEach(
                (k, v) -> {
                    if (k.equals("Program")) {
                        inputVo.setProgramid(v);
                    }
                    if (k.equals("provider")) {
                        inputVo.setProviderid(v);
                    }
                    if (k.equals("limit")) {
                        inputVo.setLimit(Integer.valueOf(v));
                    }

                });

        return inputVo;


    }
}
