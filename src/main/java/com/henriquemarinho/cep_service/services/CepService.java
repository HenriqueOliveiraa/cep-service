package com.henriquemarinho.cep_service.services;

import com.henriquemarinho.cep_service.dto.CepResponseDTO;

public interface CepService {
    CepResponseDTO buscarCep(String cep);
}
