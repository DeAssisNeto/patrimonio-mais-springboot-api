package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.models.LocationPatrimonyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationPatrimonyRepository extends JpaRepository<LocationPatrimonyModel, UUID> {
    List<LocationPatrimonyModel> findAllByPatrimonyId(UUID patrimonyId);
    Page<LocationPatrimonyModel> findAllByActiveTrue(Pageable pageable);
    Optional<LocationPatrimonyModel> findByIdAndActiveTrue(UUID id);
}
