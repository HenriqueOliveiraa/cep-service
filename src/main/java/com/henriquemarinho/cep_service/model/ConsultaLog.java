package com.henriquemarinho.cep_service.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String cep;
    @Column(length = 255)
    private String logradouro;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 2)
    private String uf;

    @Column(nullable = false)
    private LocalDateTime horarioConsulta;
}
