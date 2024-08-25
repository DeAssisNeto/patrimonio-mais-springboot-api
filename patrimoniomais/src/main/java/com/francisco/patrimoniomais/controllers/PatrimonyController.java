package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.PatrimonyRecordDto;
import com.francisco.patrimoniomais.enums.GroupEnum;
import com.francisco.patrimoniomais.enums.SubgroupEnum;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.services.PatrimonyService;
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
@RequestMapping("/api/patrimony")
public class PatrimonyController {
    @Autowired
    private PatrimonyService patrimonyService;


    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody PatrimonyRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(patrimonyService.save(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<PatrimonyModel>> getAll(@PageableDefault(
            page = 0, size =10,sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(patrimonyService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(new ApiGlobalResponseDto(patrimonyService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@PathVariable(value = "id") UUID id, @RequestBody PatrimonyRecordDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(patrimonyService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        patrimonyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/by-subgroup")
    public ResponseEntity<Page<PatrimonyModel>> getAllBySubgroup(@RequestParam SubgroupEnum subgroup,
                                                              @PageableDefault(
                                                                      page = 0,
                                                                      size =10,
                                                                      sort = "id",
                                                                      direction = Sort.Direction.ASC ) Pageable pageable
                                                              ){
        return ResponseEntity.ok(patrimonyService.findAllBySubgroup(subgroup, pageable));
    }

    @GetMapping("/by-group")
    public ResponseEntity<Page<PatrimonyModel>> getAllByGroup(@RequestParam GroupEnum group,
                                                              @PageableDefault(
                                                                      page = 0,
                                                                      size =10,
                                                                      sort = "id",
                                                                      direction = Sort.Direction.ASC ) Pageable pageable
                                                              ){
        return ResponseEntity.ok(patrimonyService.findAllByGroup(group, pageable));
    }


}
