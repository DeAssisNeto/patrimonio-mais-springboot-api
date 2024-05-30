package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.CompanyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public CompanyModel save(CompanyRecordDto dto){
        return companyRepository.save(new CompanyModel(dto.name(), dto.cnpj(), dto.description()));
    }

    public Page<CompanyModel> getAll(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public CompanyModel getById(UUID id){
        Optional<CompanyModel> model =  companyRepository.findById(id);
        if (model.isPresent()) {return model.get();}
        throw new ResourceNotFoundException("Company", "id", id.toString());
    }

    @Transactional
    public CompanyModel update(UUID id, CompanyRecordDto dto){
        Optional<CompanyModel> model = companyRepository.findById(id);
        if (model.isPresent()){
            dto.toModel(model.get());
            companyRepository.save(model.get());
            return model.get();
        }
        throw new ResourceNotFoundException("Company", "id", id.toString());
    }

    @Transactional
    public void delete(UUID id){
        Optional<CompanyModel> model = companyRepository.findById(id);
        if (model.isEmpty()) throw new ResourceNotFoundException("Company", "id", id.toString());
        model.get().setActive(false);
    }

}
