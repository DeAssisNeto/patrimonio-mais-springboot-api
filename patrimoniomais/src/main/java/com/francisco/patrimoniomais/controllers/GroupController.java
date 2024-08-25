package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.GroupRecordDto;
import com.francisco.patrimoniomais.dtos.LocationPatrimonyRecordDto;
import com.francisco.patrimoniomais.models.GroupModel;
import com.francisco.patrimoniomais.models.LocationPatrimonyModel;
import com.francisco.patrimoniomais.services.GroupService;
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
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody GroupRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(groupService.save(dto)));
    }


    @GetMapping
    public ResponseEntity<Page<GroupModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(groupService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(new ApiGlobalResponseDto(groupService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody GroupRecordDto dto, @PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(groupService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id){
        groupService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
