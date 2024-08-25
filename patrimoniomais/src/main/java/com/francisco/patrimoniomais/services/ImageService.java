package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.CompanyRecordDto;
import com.francisco.patrimoniomais.dtos.ImageRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.models.ImageModel;
import com.francisco.patrimoniomais.models.PatrimonyModel;
import com.francisco.patrimoniomais.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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

    public ImageModel getById(UUID id){
        Optional<ImageModel> model =  imageRepository.findById(id);
        if (model.isPresent()) {return model.get();}
        throw new ResourceNotFoundException("Image", "id", id.toString());
    }

    @Transactional
    public ImageModel update(UUID id, ImageRecordDto dto){
        Optional<ImageModel> imageOptional = imageRepository.findById(id);
        if (imageOptional.isEmpty()) throw new ResourceNotFoundException("Company", "id", id.toString());

        ImageModel imageModel = imageOptional.get();
        if (dto.patrimonyId() != null) imageModel.setPatrimony(patrimonyService.getById(dto.patrimonyId()));
        if (dto.path() != null) imageModel.setPath(dto.path());
        return imageRepository.save(imageModel);
    }

    @Transactional
    public void delete(UUID id){
        Optional<ImageModel> model = imageRepository.findById(id);
        if (model.isEmpty()) throw new ResourceNotFoundException("Image", "id", id.toString());
        imageRepository.deleteById(id);
    }
}
