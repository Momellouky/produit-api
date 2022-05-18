package com.nawratech.back.produit.exceptionHandlers;

import javax.websocket.OnError;

public class HttpUnprocessableEntityException extends RuntimeException{

    private String message ;
    public HttpUnprocessableEntityException(ErrorMessages errorMessages){

        if(errorMessages == ErrorMessages.INVALID_QUERY_PARAM_LIMIT){
            message = "Query parameter limit has an invalid value.";
        }else if(errorMessages == ErrorMessages.INVALID_QUERY_PARAM_ORDER){
            message = "Query parameter order has an invalid value.";
        }else if(errorMessages == ErrorMessages.PRODUCT_EXISTS){
            message = "This product already exists.";
        }

    }

    @Override
    public String getMessage(){
        return message;
    }

}
