package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.CompanyPostDto;
import com.francisco.patrimoniomais.dtos.CompanyPutDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyModel save(CompanyPostDto dto){
        return companyRepository.save(new CompanyModel(dto.name(), dto.cnpj(), dto.description()));
    }

    public Page<CompanyModel> getAll(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public CompanyModel update(UUID id, CompanyPutDto dto){
        Optional<CompanyModel> model = companyRepository.findById(id);
        if (model.isPresent()){
            dto.toModel(model.get());
            companyRepository.save(model.get());
            return model.get();
        }
        throw new ResourceNotFoundException("Company", "id", id.toString());
    }

    public void delete(UUID id){
        if (!companyRepository.existsById(id)) throw new ResourceNotFoundException("Company", "id", id.toString());
        companyRepository.deleteById(id);
    }

}
