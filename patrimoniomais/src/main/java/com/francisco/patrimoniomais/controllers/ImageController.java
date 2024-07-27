package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.ImageRecordDto;
import com.francisco.patrimoniomais.models.ImageModel;
import com.francisco.patrimoniomais.services.ImageService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> save(@RequestBody ImageRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(imageService.save(dto)));
    }


    @GetMapping
    public ResponseEntity<Page<ImageModel>> getAll(@PageableDefault(
            page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(imageService.findAll(pageable));
    }
}
