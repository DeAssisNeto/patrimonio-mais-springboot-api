package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.SubsectorPostDto;
import com.francisco.patrimoniomais.dtos.SubsectorPutDto;
import com.francisco.patrimoniomais.models.SubsectorModel;
import com.francisco.patrimoniomais.services.SubsectorService;
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
@RequestMapping("/api/subsector")
public class SubsectorController {
    @Autowired
    private SubsectorService subsectorService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody SubsectorPostDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(subsectorService.save(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<SubsectorModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(subsectorService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody SubsectorPutDto dto, @PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(subsectorService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        subsectorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
