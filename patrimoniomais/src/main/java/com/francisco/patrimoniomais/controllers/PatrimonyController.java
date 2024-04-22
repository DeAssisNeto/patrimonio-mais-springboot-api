package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.PatrimonyPutRecordDto;
import com.francisco.patrimoniomais.dtos.PatrimonyRecordDto;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.services.PatrimonyService;
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
public class PatrimonyController {
    @Autowired
    private PatrimonyService patrimonyService;



    @GetMapping
    public ResponseEntity<Page<PatrimonyModel>> getAllEquipment(@PageableDefault(
            page = 0, size =10,sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(patrimonyService.findAllEquipment(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getOneEquipment(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(patrimonyService.findById(id)));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipment(@PathVariable(value = "id") UUID id){
        patrimonyService.deleteEquipment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
