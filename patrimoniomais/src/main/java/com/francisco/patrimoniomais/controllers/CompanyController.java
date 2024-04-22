package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping
    public ResponseEntity<Page<CompanyModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(companyRepository.findAll(pageable));
    }
}
