package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.SubgroupRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.SubgroupModel;
import com.francisco.patrimoniomais.repositories.SubgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubgroupService {
    @Autowired
    private SubgroupRepository subgroupRepository;
    @Autowired
    private GroupService groupService;


    public SubgroupModel save(SubgroupRecordDto dto) {
        return subgroupRepository.save(new SubgroupModel(dto.name(), groupService.getById(dto.groupId())));
    }

    public Page<SubgroupModel> findAll(Pageable pageable) {
        return subgroupRepository.findAll(pageable);
    }

    public SubgroupModel getById(UUID id){
        Optional<SubgroupModel> model =  subgroupRepository.findById(id);
        if (model.isPresent()) {return model.get();}
        throw new ResourceNotFoundException("Subgroup", "id", id.toString());
    }

    @Transactional
    public SubgroupModel update(UUID id, SubgroupRecordDto dto){
        SubgroupModel subgroupModel = this.getById(id);

        if (dto.name() != null) subgroupModel.setName(dto.name());
        if (dto.groupId() != null) subgroupModel.setGroup(groupService.getById(dto.groupId()));

        return subgroupRepository.save(subgroupModel);
    }

    @Transactional
    public void delete(UUID id){
        SubgroupModel subgroupModel = this.getById(id);
        subgroupRepository.deleteById(id);
    }
}