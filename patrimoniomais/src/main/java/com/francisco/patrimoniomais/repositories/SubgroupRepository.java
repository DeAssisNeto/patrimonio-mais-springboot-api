package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.LocationModel;
import com.francisco.patrimoniomais.models.SubgroupModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubgroupRepository extends JpaRepository<SubgroupModel, UUID> {
    Page<SubgroupModel> findAllByActiveTrue(Pageable pageable);
    Optional<SubgroupModel> findByIdAndActiveTrue(UUID id);
}
