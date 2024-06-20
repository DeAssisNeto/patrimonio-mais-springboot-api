package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.LocationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, UUID> {
    Page<LocationModel> findAllByActiveTrue(Pageable pageable);
}
