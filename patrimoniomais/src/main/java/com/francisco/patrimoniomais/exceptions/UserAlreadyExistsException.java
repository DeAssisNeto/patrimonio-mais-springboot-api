package com.francisco.patrimoniomais.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String login){
        super(String.format("Já existe um usuário cadastrado com o login - %s", login));
    }
}
