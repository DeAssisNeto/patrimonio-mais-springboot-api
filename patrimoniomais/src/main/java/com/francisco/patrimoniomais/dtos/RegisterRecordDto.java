package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.enums.GenderEnum;
import com.francisco.patrimoniomais.roles.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRecordDto(
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String name,
        @NotNull(message ="O campo não pode ser nulo")
        GenderEnum gender,
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String login,
        @NotNull(message ="O campo não pode ser nulo")
        @NotBlank(message = "O campo não pode estar em branco")
        String password,
        @NotNull(message ="O campo não pode ser nulo")
        UserRole role) {
}
