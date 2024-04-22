package com.francisco.patrimoniomais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubsectorPostDto(    @NotNull(message ="O campo não pode ser nulo")
                                   @NotBlank(message = "O campo não pode estar em branco")
                                   String name,
                                   @NotNull(message ="O campo não pode ser nulo")
                                   UUID sector_id) {

}
