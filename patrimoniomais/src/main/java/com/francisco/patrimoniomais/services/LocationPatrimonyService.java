package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.LocationPatrimonyRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.*;
import com.francisco.patrimoniomais.repositories.LocationPatrimonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;



@Service
public class LocationPatrimonyService {
    @Autowired
    private LocationPatrimonyRepository locationPatrimonyRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private PatrimonyService patrimonyService;

    public LocationPatrimonyModel save(LocationPatrimonyRecordDto dto) {
        LocationModel locationModel = locationService.getById(dto.locationId());
        PatrimonyModel patrimonyModel = patrimonyService.getById(dto.patrimonyId());
        return locationPatrimonyRepository.save(new LocationPatrimonyModel(locationModel, patrimonyModel));
    }

    public Page<LocationPatrimonyModel> findAll(Pageable pageable) {
        return locationPatrimonyRepository.findAll(pageable);
    }

    @Transactional
    public LocationPatrimonyModel update(UUID id, LocationPatrimonyRecordDto dto){
        Optional<LocationPatrimonyModel> locationPatrimonyOptional = locationPatrimonyRepository.findById(id);
        if (locationPatrimonyOptional.isEmpty()) throw new ResourceNotFoundException("LocationPatrimony", "id", id.toString());

        LocationPatrimonyModel locationPatrimonyModel = locationPatrimonyOptional.get();

        if (dto.locationId() != null) locationPatrimonyModel.setLocation(locationService.getById(dto.locationId()));
        if (dto.patrimonyId() != null) locationPatrimonyModel.setPatrimony(patrimonyService.getById(dto.patrimonyId()));

        return locationPatrimonyRepository.save(locationPatrimonyModel);
    }

    @Transactional
    public void delete(UUID id){
        Optional<LocationPatrimonyModel> model = locationPatrimonyRepository.findById(id);
        if (model.isEmpty()) throw new ResourceNotFoundException("LocationPatrimony", "id", id.toString());
        locationPatrimonyRepository.deleteById(id);
    }
}
