package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.EquipmentPutRecordDto;
import com.francisco.patrimoniomais.dtos.EquipmentRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.EquipmentModel;
import com.francisco.patrimoniomais.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    public Page<EquipmentModel> findAllEquipment(Pageable pageable){
        return equipmentRepository.findAll(pageable);
    }

    public EquipmentModel saveEquipment(EquipmentRecordDto dto){

        return equipmentRepository.save(new EquipmentModel(generateUniqueCode(), dto.name(), dto.description(), dto.imagePath()));
    }

    public EquipmentModel findById(UUID id){
        Optional<EquipmentModel> model = equipmentRepository.findById(id);
        if (model.isPresent()){
            return model.get();
        }
        throw new ResourceNotFoundException("Equipment", "id", id.toString());

    }

    public EquipmentModel updateEquipment(UUID id, EquipmentPutRecordDto dto){
        Optional<EquipmentModel> model = equipmentRepository.findById(id);
        if (model.isPresent()){
            dto.toEquipment(model.get() );
            equipmentRepository.save(model.get());
            return model.get();
        }
        throw new ResourceNotFoundException("Equipment", "id", id.toString());

    }

    public void deleteEquipment(UUID id){
        if (!equipmentRepository.existsById(id)){
            throw new ResourceNotFoundException("Equipment", "id", id.toString());
        }
        equipmentRepository.deleteById(id);
    }

    private String generateUniqueCode(){
        String code = Long.toString(new Random().nextLong());
        if (equipmentRepository.existsByCode(code)){
            generateUniqueCode();
        }
        return code;
    }

}
