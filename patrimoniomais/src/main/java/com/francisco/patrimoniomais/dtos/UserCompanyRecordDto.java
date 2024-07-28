package com.francisco.patrimoniomais.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserCompanyRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        UUID userId,
        @NotNull(message ="O campo não pode ser nulo")
        UUID companyId
) {
}
