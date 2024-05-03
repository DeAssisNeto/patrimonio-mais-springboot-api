package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.PatrimonyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatrimonyRepository extends JpaRepository<PatrimonyModel, UUID> {

}
