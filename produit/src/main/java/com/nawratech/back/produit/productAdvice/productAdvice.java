package com.nawratech.back.produit.productAdvice;

import com.nawratech.back.produit.exceptionHandlers.HttpBadRequestException;
import com.nawratech.back.produit.exceptionHandlers.RessourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class productAdvice {

    @ResponseBody
    @ExceptionHandler(RessourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFound(RessourceNotFoundException ex) {
        return ex.getMessage(); 
    }

    @ResponseBody
    @ExceptionHandler(HttpBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequest(HttpBadRequestException ex){
        return ex.getMessage();
    }
}
