package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByLogin(String login);
    boolean existsByLogin(String login);
    Page<UserModel> findAllByActiveTrue(Pageable pageable);
}
