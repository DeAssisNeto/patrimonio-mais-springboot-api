package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.enums.SubgroupEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubgroupRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        SubgroupEnum name,
        @NotNull(message ="O campo não pode ser nulo")
        UUID groupId
) {
}
