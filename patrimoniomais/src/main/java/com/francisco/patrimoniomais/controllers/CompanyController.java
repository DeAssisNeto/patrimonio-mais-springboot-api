package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.CompanyPostDto;
import com.francisco.patrimoniomais.dtos.CompanyPutDto;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.services.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody @Valid CompanyPostDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(companyService.save(dto)));

    }

    @GetMapping
    public ResponseEntity<Page<CompanyModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody CompanyPutDto dto, @PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(companyService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
