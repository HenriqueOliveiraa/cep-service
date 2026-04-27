package com.henriquemarinho.cep_service.exceptions;

public class CepNotFoundException extends RuntimeException{
    public CepNotFoundException(String cep){
        super("CEP não encontrado: " + cep);
    }
}
