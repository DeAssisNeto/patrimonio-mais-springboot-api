package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.enums.GroupEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatrimonyRecordDto(@NotNull(message = "O campo não pode ser nulo")
                                  @NotBlank(message = "O campo não pode estar em branco")
                                  String name,
                                 @NotNull(message = "O campo não pode ser nulo")
                                  @NotBlank(message = "O campo não pode estar em branco")
                                  String description,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 String serialNumber,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 LocalDateTime acquisitionDate,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 BigDecimal acquisitionValue,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 GroupEnum groupType,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 UUID userId,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 UUID tombamentoId,
                                 @NotNull(message = "O campo não pode ser nulo")
                                 @NotBlank(message = "O campo não pode estar em branco")
                                 UUID companyId
                                 ) {
}
