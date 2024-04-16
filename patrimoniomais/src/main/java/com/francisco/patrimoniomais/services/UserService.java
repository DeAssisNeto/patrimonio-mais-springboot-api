package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.RegisterRecordDto;
import com.francisco.patrimoniomais.exceptions.InvalidPasswordException;
import com.francisco.patrimoniomais.exceptions.UserAlreadyExistsException;
import com.francisco.patrimoniomais.models.UserModel;
import com.francisco.patrimoniomais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel save(RegisterRecordDto dto){
        if (userRepository.existsByLogin(dto.login())) throw new UserAlreadyExistsException(dto.login());
        validatePassword(dto.password());
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var newUser = new UserModel(dto.name(), dto.gender(), dto.login(), encryptedPassword, dto.role());
        return userRepository.save(newUser);
    }

    private void validatePassword(String password){
        Pattern pattern = Pattern.compile("[!@$#%&*]");
        Matcher matcher = pattern.matcher(password);

        if(password.length() <= 4 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\d.*") ||
                !matcher.find()) {
            throw new InvalidPasswordException
                    ("Erro, a senha deve conter mais de 4 caracters, pelo menos um maiúsculo, minúsculo e um caracter especial -> !@$#%&*");
        }

    }

}
