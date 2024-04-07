package com.francisco.patrimoniomais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String login,
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String password) {

}
