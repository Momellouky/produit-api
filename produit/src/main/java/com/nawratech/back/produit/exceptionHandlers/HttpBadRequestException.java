package com.nawratech.back.produit.exceptionHandlers;

public class HttpBadRequestException extends RuntimeException{

    public HttpBadRequestException() {
        super("Incorrect body! Send correct data");
    }

    public HttpBadRequestException(Long id){
        super("Product id can't be negative");
    }
    public HttpBadRequestException(String productName){
        super("Product name is not valid, it is null or its length is equal to 0, or greater then the maximum length " +
                "allowed in the database ( 100 characters )");
    }
}
