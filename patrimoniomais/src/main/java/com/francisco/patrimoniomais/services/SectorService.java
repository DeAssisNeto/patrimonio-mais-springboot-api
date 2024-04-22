package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.SectorPostDto;
import com.francisco.patrimoniomais.dtos.SectorPutDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.models.SectorModel;
import com.francisco.patrimoniomais.repositories.CompanyRepository;
import com.francisco.patrimoniomais.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private CompanyService companyService;

    public SectorModel save(SectorPostDto dto){
        return sectorRepository.save(new SectorModel(dto.name(), companyService.getById(dto.company_id())));
    }

    public Page<SectorModel> getAll(Pageable pageable){
        return sectorRepository.findAll(pageable);
    }

    public SectorModel update(UUID id, SectorPutDto dto){
        Optional<SectorModel> sectorModel = sectorRepository.findById(id);
        if(sectorModel.isPresent()){
            SectorModel model = sectorModel.get();
            if (dto.name() != null) model.setName(dto.name());
            if (dto.company_id() != null) model.setCompany(companyService.getById(dto.company_id()));
            return sectorRepository.save(sectorModel.get());
        }
        throw new ResourceNotFoundException("Sector", "id", id.toString());
    }

    public void deleteById(UUID id){
        Optional<SectorModel> model = sectorRepository.findById(id);
        if (model.isEmpty()){
            throw new ResourceNotFoundException("Sector", "id", id.toString());
        }
        sectorRepository.deleteById(id);
    }

}
