package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.TombamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TombamentoRepository extends JpaRepository<TombamentoModel, UUID> {
}
