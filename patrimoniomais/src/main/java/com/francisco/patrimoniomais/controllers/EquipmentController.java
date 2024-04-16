package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.EquipmentPutRecordDto;
import com.francisco.patrimoniomais.dtos.EquipmentRecordDto;
import com.francisco.patrimoniomais.models.EquipmentModel;
import com.francisco.patrimoniomais.services.EquipmentService;
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
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;


    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> saveEquipment(@RequestBody @Valid EquipmentRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(equipmentService.saveEquipment(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<EquipmentModel>> getAllEquipment(@PageableDefault(
            page = 0, size =10,sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findAllEquipment(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getOneEquipment(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(equipmentService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> updateEquipment(@RequestBody @Valid EquipmentPutRecordDto dto
            , @PathVariable(value = "id") UUID id){
        System.out.println("controllerrrrrrrrrrrrrrrrr");
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(equipmentService.updateEquipment(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipment(@PathVariable(value = "id") UUID id){
        equipmentService.deleteEquipment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
