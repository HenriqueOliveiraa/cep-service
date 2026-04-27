package com.henriquemarinho.cep_service.services;


import com.henriquemarinho.cep_service.dto.CepResponseDTO;
import com.henriquemarinho.cep_service.exceptions.CepInvalidoException;
import com.henriquemarinho.cep_service.exceptions.CepNotFoundException;
import com.henriquemarinho.cep_service.model.ConsultaLog;
import com.henriquemarinho.cep_service.repositories.ConsultaLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        if (cep == null || cep.isBlank()){
            throw new CepInvalidoException(cep);
        }

        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8){
            throw new CepInvalidoException(cep);
        }

        String url = cepApiUrl + "/cep/" + cepLimpo;
        CepResponseDTO response;
        try {
            response = restTemplate.getForObject(url, CepResponseDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CepNotFoundException(cep);
        }catch (Exception e){
            throw new RuntimeException("Erro ao consultar CEP: " + e.getMessage());
        }

        if (response == null) {
            throw new CepNotFoundException(cep);
        }

            ConsultaLog log = ConsultaLog.builder()
                    .cep(tratarResponse(response.getCep()))
                    .logradouro(response.getLogradouro())
                    .bairro(response.getBairro())
                    .cidade(response.getCidade())
                    .uf(response.getUf())
                    .horarioConsulta(LocalDateTime.now())
                    .build();
            repository.save(log);
        return response;
    }
    private String tratarResponse(String valor) {
        return valor != null ? valor : "N/A";
    }
}
