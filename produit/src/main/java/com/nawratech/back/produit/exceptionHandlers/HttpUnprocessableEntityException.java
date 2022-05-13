package com.nawratech.back.produit.exceptionHandlers;

public class HttpUnprocessableEntityException extends RuntimeException{

    public HttpUnprocessableEntityException(){
        super("This entity can't be processed, review the body content");
    }

    public HttpUnprocessableEntityException(Long id){
        super("Product with id: " + id + " already exists. ");
    }

}
