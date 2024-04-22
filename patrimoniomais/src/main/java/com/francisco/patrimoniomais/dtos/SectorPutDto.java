package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.models.CompanyModel;
import com.francisco.patrimoniomais.models.SectorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SectorPutDto(String name, UUID company_id) {
}
