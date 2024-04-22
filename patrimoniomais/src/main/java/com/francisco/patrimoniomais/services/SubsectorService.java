package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.SubsectorPostDto;
import com.francisco.patrimoniomais.dtos.SubsectorPutDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.SubsectorModel;
import com.francisco.patrimoniomais.repositories.SubsectorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubsectorService {
    @Autowired
    private SubsectorRespository subsectorRespository;
    @Autowired
    private SectorService sectorService;

    @Transactional
    public SubsectorModel save(SubsectorPostDto dto){
        return subsectorRespository.save(new SubsectorModel(dto.name(), sectorService.getById(dto.sector_id())));
    }

    public Page<SubsectorModel> getAll(Pageable pageable){
        return subsectorRespository.findAll(pageable);
    }

    public SubsectorModel getById(UUID id) {
        Optional<SubsectorModel> model = subsectorRespository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        throw new ResourceNotFoundException("Subsector", "id", id.toString());
    }

    @Transactional
    public SubsectorModel update(UUID id, SubsectorPutDto dto){
        Optional<SubsectorModel> subsectorModel = subsectorRespository.findById(id);
        if (subsectorModel.isPresent()){
            SubsectorModel model = subsectorModel.get();
            if (dto.name() != null) model.setName(dto.name());
            if (dto.sector_id() != null) model.setSector(sectorService.getById(dto.sector_id()));
            return subsectorRespository.save(model);
        }
        throw new ResourceNotFoundException("Subsector", "id", id.toString());
    }

    @Transactional
    public void deleteById(UUID id){
        Optional<SubsectorModel> model = subsectorRespository.findById(id);
        if (model.isEmpty()){
            throw new ResourceNotFoundException("Subsector", "id", id.toString());
        }
        subsectorRespository.deleteById(id);
    }

}
