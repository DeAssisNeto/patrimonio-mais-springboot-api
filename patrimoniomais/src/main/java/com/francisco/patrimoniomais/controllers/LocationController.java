package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.LocationRecordDto;
import com.francisco.patrimoniomais.models.LocationModel;
import com.francisco.patrimoniomais.services.LocationService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody @Valid LocationRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(locationService.save(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<LocationModel>> getAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody LocationRecordDto dto, @PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(locationService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> deleteById(@PathVariable(value = "id") UUID id){
        locationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
