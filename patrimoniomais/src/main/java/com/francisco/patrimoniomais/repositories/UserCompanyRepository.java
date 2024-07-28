package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.UserCompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompanyModel, UUID> {
}
