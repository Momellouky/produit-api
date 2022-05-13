package com.nawratech.back.produit.exceptionHandlers;

public class HttpBadRequestException extends RuntimeException{

    public HttpBadRequestException() {
        super("Incorrect body! Send correct data");
    }

    public HttpBadRequestException(Long id){
        super("Product id can't be negative");
    }
}
