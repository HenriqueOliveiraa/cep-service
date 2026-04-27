package com.henriquemarinho.cep_service.exceptions;

public class CepInvalidoException extends RuntimeException{
    public CepInvalidoException(String cep){
        super("CEP inválido: " + cep);
    }
}
