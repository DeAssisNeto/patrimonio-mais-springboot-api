package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.SectorRecordDto;
import com.francisco.patrimoniomais.models.SectorModel;
import com.francisco.patrimoniomais.services.SectorService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
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
@RequestMapping("/api/sector")
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody @Valid SectorRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(sectorService.save(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<SectorModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody SectorRecordDto dto, @PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(sectorService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> deleteById(@PathVariable(value = "id") UUID id){
        sectorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
