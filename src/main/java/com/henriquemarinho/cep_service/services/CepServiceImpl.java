package com.henriquemarinho.cep_service.services;


import com.henriquemarinho.cep_service.dto.CepResponseDTO;
import com.henriquemarinho.cep_service.model.ConsultaLog;
import com.henriquemarinho.cep_service.repositories.ConsultaLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CepServiceImpl implements CepService {

    private final RestTemplate restTemplate;
    private final ConsultaLogRepository repository;

    @Value("${cep.api.url}")
    private String cepApiUrl;

    public CepServiceImpl(RestTemplate restTemplate,
                          ConsultaLogRepository repository){
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @Override
    public CepResponseDTO buscarCep(String cep){
        if(cep.isEmpty()){
            throw new RuntimeException("Vazia");
        }
        String url = cepApiUrl + "/cep/" + cep;
        CepResponseDTO response = restTemplate.getForObject(url, CepResponseDTO.class);

        if (response != null) {
            ConsultaLog log = ConsultaLog.builder()
                    .cep(tratarResponse(response.getCep()))
                    .logradouro(response.getLogradouro())
                    .bairro(response.getBairro())
                    .cidade(response.getCidade())
                    .uf(response.getUf())
                    .horarioConsulta(LocalDateTime.now())
                    .build();
            repository.save(log);
        }
        return response;
    }
    private String tratarResponse(String valor) {
        return valor != null ? valor : "N/A";
    }

}
