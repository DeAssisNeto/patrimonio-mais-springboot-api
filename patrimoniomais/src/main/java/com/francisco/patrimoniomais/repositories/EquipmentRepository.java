package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentModel, UUID> {
    boolean existsByCode(String code);

}
