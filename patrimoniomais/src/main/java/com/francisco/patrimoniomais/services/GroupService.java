package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.GroupRecordDto;
import com.francisco.patrimoniomais.dtos.LocationPatrimonyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.GroupModel;
import com.francisco.patrimoniomais.models.LocationModel;
import com.francisco.patrimoniomais.models.LocationPatrimonyModel;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;


    public GroupModel save(GroupRecordDto dto) {
        return groupRepository.save(new GroupModel(dto.name()));
    }

    public Page<GroupModel> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Transactional
    public GroupModel update(UUID id, GroupRecordDto dto){
        Optional<GroupModel> groupOptional = groupRepository.findById(id);

        if (groupOptional.isEmpty()) throw new ResourceNotFoundException("Group", "id", id.toString());
        if (dto.name() != null) groupOptional.get().setName(dto.name());

        return groupRepository.save(groupOptional.get());
    }

    @Transactional
    public void delete(UUID id){
        Optional<GroupModel> model = groupRepository.findById(id);
        if (model.isEmpty()) throw new ResourceNotFoundException("Group", "id", id.toString());
        groupRepository.deleteById(id);
    }
}