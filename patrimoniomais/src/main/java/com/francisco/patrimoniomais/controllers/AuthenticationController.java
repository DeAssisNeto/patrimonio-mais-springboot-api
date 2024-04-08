package com.francisco.patrimoniomais.controllers;

import com.francisco.patrimoniomais.dtos.AuthenticationRecordDto;
import com.francisco.patrimoniomais.dtos.RegisterRecordDto;
import com.francisco.patrimoniomais.models.UserModel;
import com.francisco.patrimoniomais.services.TokenService;
import com.francisco.patrimoniomais.services.UserService;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiGlobalResponseDto> login(@RequestBody @Valid AuthenticationRecordDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiGlobalResponseDto> register(@RequestBody @Valid RegisterRecordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(userService.save(dto)));
    }
}
