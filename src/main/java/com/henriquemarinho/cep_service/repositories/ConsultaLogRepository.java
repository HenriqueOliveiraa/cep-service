package com.henriquemarinho.cep_service.repositories;

import com.henriquemarinho.cep_service.model.ConsultaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaLogRepository extends JpaRepository<ConsultaLog, Long> {
}
