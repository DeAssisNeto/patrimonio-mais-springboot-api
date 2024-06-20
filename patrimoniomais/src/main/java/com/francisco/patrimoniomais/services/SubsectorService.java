package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.SubsectorRecordDto;
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
    public SubsectorModel save(SubsectorRecordDto dto){
        return subsectorRespository.save(new SubsectorModel(dto.name(), sectorService.getById(dto.sectorId())));
    }

    public Page<SubsectorModel> getAll(Pageable pageable){
        return subsectorRespository.findAllByActiveTrue(pageable);
    }

    public SubsectorModel getById(UUID id) {
        Optional<SubsectorModel> model = subsectorRespository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        throw new ResourceNotFoundException("Subsector", "id", id.toString());
    }

    @Transactional
    public SubsectorModel update(UUID id, SubsectorRecordDto dto){
        Optional<SubsectorModel> subsectorModel = subsectorRespository.findById(id);
        if (subsectorModel.isPresent()){
            SubsectorModel model = subsectorModel.get();
            if (dto.name() != null) model.setName(dto.name());
            if (dto.sectorId() != null) model.setSector(sectorService.getById(dto.sectorId()));
            return subsectorRespository.save(model);
        }
        throw new ResourceNotFoundException("Subsector", "id", id.toString());
    }

    @Transactional
    public void deleteById(UUID id){
        Optional<SubsectorModel> model = subsectorRespository.findById(id);
        if (model.isEmpty()){throw new ResourceNotFoundException("Subsector", "id", id.toString());}
        model.get().setActive(false);
    }

}
