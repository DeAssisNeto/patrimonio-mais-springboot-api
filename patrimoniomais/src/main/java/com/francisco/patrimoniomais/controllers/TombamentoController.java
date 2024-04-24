package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.TombamentoRecordDto;
import com.francisco.patrimoniomais.models.TombamentoModel;
import com.francisco.patrimoniomais.services.TombamentoService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tombamento")
public class TombamentoController {
    @Autowired
    private TombamentoService tombamentoService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody @Valid TombamentoRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(tombamentoService.save(dto)));
    }

    @GetMapping
    public ResponseEntity<Page<TombamentoModel>> getAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(tombamentoService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> update(@RequestBody TombamentoRecordDto dto, @PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(tombamentoService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> deleteById(@PathVariable(value = "id")UUID id){
        tombamentoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
