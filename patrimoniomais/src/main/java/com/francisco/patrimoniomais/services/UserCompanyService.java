package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.UserCompanyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.*;
import com.francisco.patrimoniomais.repositories.UserCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserCompanyService {
    @Autowired
    private UserCompanyRepository userCompanyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    public UserCompanyModel save(UserCompanyRecordDto dto) {
        UserModel userModel = userService.getById(dto.userId());
        CompanyModel companyModel = companyService.getById(dto.companyId());
        return userCompanyRepository.save(new UserCompanyModel(userModel, companyModel));
    }

    public Page<UserCompanyModel> findAll(Pageable pageable) {
        return userCompanyRepository.findAll(pageable);
    }

    @Transactional
    public UserCompanyModel update(UUID id, UserCompanyRecordDto dto){
        Optional<UserCompanyModel> userCompanyOptional = userCompanyRepository.findById(id);
        if (userCompanyOptional.isEmpty()) throw new ResourceNotFoundException("UserCompany", "id", id.toString());

        UserCompanyModel userCompanyModel = userCompanyOptional.get();

        if (dto.userId() != null) userCompanyModel.setUser(userService.getById(dto.userId()));
        if (dto.companyId() != null) userCompanyModel.setCompany(companyService.getById(dto.companyId()));

        return userCompanyRepository.save(userCompanyModel);
    }

    @Transactional
    public void delete(UUID id){
        Optional<UserCompanyModel> model = userCompanyRepository.findById(id);
        if (model.isEmpty()) throw new ResourceNotFoundException("UserCompany", "id", id.toString());
        userCompanyRepository.deleteById(id);
    }
}