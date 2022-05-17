package com.nawratech.back.produit.exceptionHandlers;

public class HttpBadRequestException extends RuntimeException{

    private String message;
    public HttpBadRequestException() {

    }

    public HttpBadRequestException(Long id){
        super("Product id can't be negative");
    }
    public HttpBadRequestException(String productName){
        super("Product name is not valid, it is null or its length is equal to 0, or greater then the maximum length " +
                "allowed in the database ( 100 characters )");
    }

    public HttpBadRequestException(int productQuantity){
        super("Product quantity can't be negative");
    }

    public HttpBadRequestException(ErrorMessages errorMessage){

        if(errorMessage == ErrorMessages.INVALID_NAME){
            message = new String("Product name is not valid, it is null or its length is equal to 0, or greater then the maximum length " +
                    "allowed in the database ( 100 characters )");
        }else if(errorMessage == ErrorMessages.NEGATIVE_ID){
            message = new String("Product id can't be negative");
        }else if(errorMessage == ErrorMessages.NEGATIVE_QUANTITY){
            message = new String("Product quantity can't be negative");
        }else if(errorMessage == ErrorMessages.INVALID_QUERY_PARAM_LIMIT){
            message = new String("Limit value must be positive");
        }

    }

    @Override
    public String getMessage(){
        return message;
    }
}
