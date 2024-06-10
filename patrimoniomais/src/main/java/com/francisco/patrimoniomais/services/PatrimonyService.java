package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.PatrimonyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.models.TombamentoModel;
import com.francisco.patrimoniomais.models.UserModel;
import com.francisco.patrimoniomais.repositories.PatrimonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PatrimonyService {
    @Autowired
    private PatrimonyRepository patrimonyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TombamentoService tombamentoService;
    @Autowired
    private CompanyService companyService;


    public PatrimonyModel save(PatrimonyRecordDto dto){
        UserModel user = userService.getById(dto.userId());
        TombamentoModel tombamento = tombamentoService.getById(dto.tombamentoId());
        CompanyModel company = companyService.getById(dto.companyId());

        return patrimonyRepository.save(new PatrimonyModel(dto.name(), dto.description(), dto.serialNumber(),
                dto.acquisitionDate(), dto.acquisitionValue(), dto.groupType(), user, tombamento,
                company));
    }

    public Page<PatrimonyModel> getAll(Pageable pageable){
        return patrimonyRepository.findAll(pageable);
    }


    public PatrimonyModel getById(UUID id){
        Optional<PatrimonyModel> model = patrimonyRepository.findById(id);
        if (model.isPresent()){
            return model.get();
        }
        throw new ResourceNotFoundException("Patrimony", "id", id.toString());

    }

    public PatrimonyModel update(UUID id, PatrimonyRecordDto dto){
        Optional<PatrimonyModel> patrimonyModel = patrimonyRepository.findById(id);
        if (patrimonyModel.isPresent()){
            PatrimonyModel model = patrimonyModel.get();
            if (dto.name()!= null) model.setName(dto.name());
            if (dto.description()!= null) model.setDescription(dto.description());
            if (dto.serialNumber()!= null) model.setSerialNumber(dto.serialNumber());
            if (dto.acquisitionDate()!= null) model.setAcquisitionDate(dto.acquisitionDate());
            if (dto.acquisitionValue()!= null) model.setAcquisitionValue(dto.acquisitionValue());
            if (dto.groupType()!= null) model.setGroupType(dto.groupType());
            if (dto.userId()!= null) model.setUserAt(userService.getById(dto.userId()));
            if (dto.tombamentoId()!= null) model.setTombamento(tombamentoService.getById(dto.tombamentoId()));
            if (dto.companyId()!= null) model.setCompany(companyService.getById(dto.companyId()));
            return patrimonyRepository.save(model);
        }
        throw new ResourceNotFoundException("Patrymony", "id", id.toString());
    }




    public void deleteById(UUID id){
        Optional<PatrimonyModel> model = patrimonyRepository.findById(id);
        if (model.isEmpty()){throw new ResourceNotFoundException("Patrimony", "id", id.toString());}
        model.get().setActive(false);
    }



}
