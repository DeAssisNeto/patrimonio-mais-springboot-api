package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.GroupRecordDto;
import com.francisco.patrimoniomais.dtos.SubgroupRecordDto;
import com.francisco.patrimoniomais.models.GroupModel;
import com.francisco.patrimoniomais.models.SubgroupModel;
import com.francisco.patrimoniomais.services.GroupService;
import com.francisco.patrimoniomais.services.SubgroupService;
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
@RequestMapping("/api/subgroup")
public class SubgroupController {
    @Autowired
    private SubgroupService subgroupService;


    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody SubgroupRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(subgroupService.save(dto)));
    }


    @GetMapping
    public ResponseEntity<Page<SubgroupModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(subgroupService.findAll(pageable));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody SubgroupRecordDto dto, @PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(subgroupService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        subgroupService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}