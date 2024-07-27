package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.ImageRecordDto;
import com.francisco.patrimoniomais.models.ImageModel;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private PatrimonyService patrimonyService;

    @Autowired
    private ImageRepository imageRepository;

    public ImageModel save(ImageRecordDto dto) {
        PatrimonyModel patrimonyModel = patrimonyService.getById(dto.patrimonyId());
        return imageRepository.save(new ImageModel(dto.path(), patrimonyModel));
    }

    public Page<ImageModel> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }
}
