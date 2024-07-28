package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.LocationPatrimonyRecordDto;
import com.francisco.patrimoniomais.models.LocationPatrimonyModel;
import com.francisco.patrimoniomais.services.LocationPatrimonyService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/locationPatrimony")
public class LocationPatrimonyController {
    @Autowired
    private LocationPatrimonyService locationPatrimonyService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody LocationPatrimonyRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(locationPatrimonyService.save(dto)));
    }


    @GetMapping
    public ResponseEntity<Page<LocationPatrimonyModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(locationPatrimonyService.findAll(pageable));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody LocationPatrimonyRecordDto dto, @PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(locationPatrimonyService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        locationPatrimonyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}