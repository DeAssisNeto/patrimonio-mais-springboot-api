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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
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

    @Transactional
    public LocationPatrimonyModel save(LocationPatrimonyRecordDto dto) {
        LocationModel locationModel = locationService.getById(dto.locationId());
        PatrimonyModel patrimonyModel = patrimonyService.getById(dto.patrimonyId());
        return locationPatrimonyRepository.save(
                new LocationPatrimonyModel(
                        locationModel, patrimonyModel, LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
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

    public List<LocationPatrimonyModel> getLastThree(UUID patrimonyId){
        List<LocationPatrimonyModel> locationPatrimonies = locationPatrimonyRepository.findAllByPatrimonyId(patrimonyId);
        Comparator<LocationPatrimonyModel> comparator = Comparator.comparing(LocationPatrimonyModel::getCreateAt);
        return locationPatrimonies.stream()
                .sorted(comparator.reversed())
                .limit(3)
                .toList();


    }

}
