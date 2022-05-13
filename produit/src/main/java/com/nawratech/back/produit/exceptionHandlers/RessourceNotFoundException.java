package com.nawratech.back.produit.exceptionHandlers;

public class RessourceNotFoundException extends RuntimeException{

    public RessourceNotFoundException() {
        super("Ressource Not found");
    }
}
