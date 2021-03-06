package com.nawratech.back.produit.exceptionHandlers;

public class HttpBadRequestException extends RuntimeException{

    private String message;
    public HttpBadRequestException() {

    }


    public HttpBadRequestException(ErrorMessages errorMessage){

        if(errorMessage == ErrorMessages.INVALID_NAME){
            message = new String("Product name is not valid, its length is equal to 0, or greater then the maximum length " +
                    "allowed in the database ( 100 characters )");
        }else if(errorMessage == ErrorMessages.NEGATIVE_ID){
            message = new String("Product id must be positive");
        }else if(errorMessage == ErrorMessages.NEGATIVE_QUANTITY){
            message = new String("Product quantity can't be negative ( qte >= 0 )");
        }else if(errorMessage == ErrorMessages.INVALID_QUERY_PARAM_LIMIT){
            message = new String("Limit value must be positive");
        }else if(errorMessage == ErrorMessages.NULL_ID){
            message = new String("Product id key should be : id");
        }else if(errorMessage == ErrorMessages.NULL_NAME){
            message = new String("Product name key should be : name");
        }


    }

    @Override
    public String getMessage(){
        return message;
    }
}
