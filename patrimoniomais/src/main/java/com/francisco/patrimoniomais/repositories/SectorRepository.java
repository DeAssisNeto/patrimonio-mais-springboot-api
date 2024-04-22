package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SectorRepository extends JpaRepository<SectorModel, UUID> {

}
