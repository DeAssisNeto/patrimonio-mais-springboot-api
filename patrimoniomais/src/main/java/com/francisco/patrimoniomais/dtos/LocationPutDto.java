package com.francisco.patrimoniomais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LocationPutDto(String name, UUID subsector_id) {
}
