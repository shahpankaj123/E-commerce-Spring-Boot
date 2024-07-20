package com.pankaj.ecommerce.Exception;


public class ErrorResponse {

    private String message;


    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    // Getters and Setters

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

