package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.UserCompanyRecordDto;
import com.francisco.patrimoniomais.models.UserCompanyModel;
import com.francisco.patrimoniomais.services.UserCompanyService;
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
@RequestMapping("/api/userCompany")
public class UserCompanyController {
    @Autowired
    private UserCompanyService userCompanySevice;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody UserCompanyRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(userCompanySevice.save(dto)));
    }


    @GetMapping
    public ResponseEntity<Page<UserCompanyModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(userCompanySevice.findAll(pageable));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody UserCompanyRecordDto dto, @PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(userCompanySevice.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        userCompanySevice.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}