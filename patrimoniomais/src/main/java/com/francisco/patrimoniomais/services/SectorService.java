package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.SectorRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.SectorModel;
import com.francisco.patrimoniomais.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private CompanyService companyService;

    @Transactional
    public SectorModel save(SectorRecordDto dto){
        return sectorRepository.save(new SectorModel(dto.name(), companyService.getById(dto.companyId())));
    }

    public Page<SectorModel> getAll(Pageable pageable){
        return sectorRepository.findAll(pageable);
    }

    public SectorModel getById(UUID id){
        Optional<SectorModel> model = sectorRepository.findById(id);
        if (model.isPresent()) return model.get();
        throw new ResourceNotFoundException("Sector", "id", id.toString());
    }

    @Transactional
    public SectorModel update(UUID id, SectorRecordDto dto){
        Optional<SectorModel> sectorModel = sectorRepository.findById(id);
        if(sectorModel.isPresent()){
            SectorModel model = sectorModel.get();
            if (dto.name() != null) model.setName(dto.name());
            if (dto.companyId() != null) model.setCompany(companyService.getById(dto.companyId()));
            return sectorRepository.save(sectorModel.get());
        }
        throw new ResourceNotFoundException("Sector", "id", id.toString());
    }

    @Transactional
    public void deleteById(UUID id){
        Optional<SectorModel> model = sectorRepository.findById(id);
        if (model.isEmpty()){throw new ResourceNotFoundException("Sector", "id", id.toString());}
        model.get().setActive(false);
    }

}
