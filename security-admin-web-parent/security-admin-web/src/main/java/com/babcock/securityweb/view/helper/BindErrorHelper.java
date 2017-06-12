package com.babcock.securityweb.view.helper;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class BindErrorHelper {

    public void outputErrors(BindingResult bindingResult, Logger logger) {

        for(Object object : bindingResult.getAllErrors()) {

            if(object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                logger.warn("validation error {}:{}",fieldError.getField(),fieldError.getCode());
            }else {
                ObjectError objectError = (ObjectError) object;
                logger.warn("validation error {}",objectError.getCode());
            }

        }
    }
}
