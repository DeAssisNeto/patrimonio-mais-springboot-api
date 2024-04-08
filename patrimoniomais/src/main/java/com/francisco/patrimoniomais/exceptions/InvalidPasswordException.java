package com.francisco.patrimoniomais.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message){
        super(message);
    }

}
