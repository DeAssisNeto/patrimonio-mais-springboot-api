package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.LocationRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.LocationModel;
import com.francisco.patrimoniomais.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SubsectorService subsectorService;

    @Transactional
    public LocationModel save(LocationRecordDto dto){

        return locationRepository.save(new LocationModel(dto.name(), subsectorService.getById(dto.subsectorId())));
    }

    public Page<LocationModel> getAll(Pageable pageable){
        return locationRepository.findAll(pageable);
    }

    @Transactional
    public LocationModel update(UUID id, LocationRecordDto dto){
        Optional<LocationModel> locationModel = locationRepository.findById(id);
        if (locationModel.isPresent()){
            LocationModel model = locationModel.get();
            if (dto.name() != null) model.setName(dto.name());
            if (dto.subsectorId() != null) model.setSubsector(subsectorService.getById(dto.subsectorId()));
            return locationRepository.save(model);
        }
        throw new ResourceNotFoundException("Location", "id", id.toString());

    }

    @Transactional
    public void deleteById(UUID id){
        Optional<LocationModel> model = locationRepository.findById(id);
        if (model.isEmpty()){
            throw new ResourceNotFoundException("Location", "id", id.toString());
        }
        locationRepository.deleteById(id);
    }


}
