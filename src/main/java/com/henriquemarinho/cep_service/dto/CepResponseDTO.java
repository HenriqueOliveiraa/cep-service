package com.henriquemarinho.cep_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepResponseDTO {

    private String cep;
    private String logradouro;
    private String bairro;

    @JsonProperty("localidade")
    private String cidade;
    private String uf;
}
