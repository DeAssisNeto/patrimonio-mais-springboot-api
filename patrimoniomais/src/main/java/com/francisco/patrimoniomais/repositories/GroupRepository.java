package com.francisco.patrimoniomais.repositories;

import com.francisco.patrimoniomais.models.GroupModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, UUID> {
    Page<GroupModel> findAllByActiveTrue(Pageable pageable);
    Optional<GroupModel> findByIdAndActiveTrue(UUID id);
}
