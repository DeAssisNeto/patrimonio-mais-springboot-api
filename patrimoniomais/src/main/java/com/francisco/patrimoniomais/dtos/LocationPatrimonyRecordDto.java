package com.francisco.patrimoniomais.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LocationPatrimonyRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        UUID locationId,
        @NotNull(message ="O campo não pode ser nulo")
        UUID patrimonyId
) {
}
