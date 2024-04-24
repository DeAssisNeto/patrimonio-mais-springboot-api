package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.TombamentoRecordDto;
import com.francisco.patrimoniomais.exceptions.ResourceNotFoundException;
import com.francisco.patrimoniomais.models.TombamentoModel;
import com.francisco.patrimoniomais.repositories.TombamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class TombamentoService {
    @Autowired
    private TombamentoRepository tombamentoRepository;

    public TombamentoModel save(TombamentoRecordDto dto){
        return tombamentoRepository.save(new TombamentoModel(dto.statusType(), dto.code(), LocalDateTime.now(ZoneId.of("UTC"))));
    }

    public Page<TombamentoModel> getAll(Pageable pageable){
        return tombamentoRepository.findAll(pageable);
    }

    public TombamentoModel update(UUID id, TombamentoRecordDto dto){
        Optional<TombamentoModel> tombamentoModel = tombamentoRepository.findById(id);
        if (tombamentoModel.isPresent()){
            TombamentoModel model = tombamentoModel.get();
            if (dto.statusType() != null) model.setStatusType(dto.statusType());
            if (dto.code() != null) model.setCode(dto.code());
            return tombamentoRepository.save(model);
        }
        throw new ResourceNotFoundException("Tombamento", "id", id.toString());
    }

    public void deleteById(UUID id){
        Optional<TombamentoModel> model = tombamentoRepository.findById(id);
        if (model.isEmpty()){
            throw new ResourceNotFoundException("Tombamento", "id", id.toString());
        }
        tombamentoRepository.deleteById(id);
    }
}
