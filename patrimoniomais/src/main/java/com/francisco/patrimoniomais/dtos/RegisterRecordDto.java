package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.roles.UserRole;

public record RegisterRecordDto(String login, String password, UserRole role) {
}
