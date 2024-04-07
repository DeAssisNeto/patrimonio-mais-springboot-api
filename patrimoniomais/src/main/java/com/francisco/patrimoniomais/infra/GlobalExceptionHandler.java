package com.francisco.patrimoniomais.infra;

import com.francisco.patrimoniomais.exceptions.UserAlreadyExistsException;
import com.francisco.patrimoniomais.utils.ApiGlobalResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<ApiGlobalResponseDto> handlerArgumenteNotValid(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiGlobalResponseDto(errors));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ApiGlobalResponseDto> userAlreadyExistsHandler(UserAlreadyExistsException exception){
        Map<String, String> error = Map.of("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiGlobalResponseDto(error));

    }
}
