package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TombamentoRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        StatusEnum statusType,
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String code
) {
}
