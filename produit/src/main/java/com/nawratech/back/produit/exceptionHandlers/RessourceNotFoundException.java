package com.nawratech.back.produit.exceptionHandlers;

public class RessourceNotFoundException extends RuntimeException{

    public RessourceNotFoundException() {
        super("Resource Not found");
    }
}
