package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.TombamentoModel;
import com.francisco.patrimoniomais.models.UserCompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompanyModel, UUID> {
    Page<UserCompanyModel> findAllByActiveTrue(Pageable pageable);
    Optional<UserCompanyModel> findByIdAndActiveTrue(UUID id);
}
