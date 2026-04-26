package com.henriquemarinho.cep_service.controllers;

import com.henriquemarinho.cep_service.dto.CepResponseDTO;
import com.henriquemarinho.cep_service.services.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService){
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep){
        CepResponseDTO response = cepService.buscarCep(cep);

        if (response == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
