package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.models.ImageModel;
import com.francisco.patrimoniomais.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Page<ImageModel> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }
}
