package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.PatrimonyPutRecordDto;
import com.francisco.patrimoniomais.dtos.PatrimonyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.repositories.PatrimonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PatrimonyService {
    @Autowired
    private PatrimonyRepository patrimonyRepository;
    public Page<PatrimonyModel> findAllEquipment(Pageable pageable){
        return patrimonyRepository.findAll(pageable);
    }

//    public PatrimonyModel saveEquipment(PatrimonyRecordDto dto){
//
//        return patrimonyRepository.save(new PatrimonyModel(generateUniqueCode(), dto.name(), dto.description(), dto.imagePath()));
//    }

    public PatrimonyModel findById(UUID id){
        Optional<PatrimonyModel> model = patrimonyRepository.findById(id);
        if (model.isPresent()){
            return model.get();
        }
        throw new ResourceNotFoundException("Equipment", "id", id.toString());

    }




    public void deleteEquipment(UUID id){
        if (!patrimonyRepository.existsById(id)){
            throw new ResourceNotFoundException("Equipment", "id", id.toString());
        }
        patrimonyRepository.deleteById(id);
    }



}
