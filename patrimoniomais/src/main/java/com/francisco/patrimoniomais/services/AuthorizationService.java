package com.francisco.patrimoniomais.services;

import com.francisco.patrimoniomais.dtos.AuthenticationRecordDto;
import com.francisco.patrimoniomais.dtos.RegisterRecordDto;
import com.francisco.patrimoniomais.models.UserModel;
import com.francisco.patrimoniomais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public UserModel save(RegisterRecordDto dto){
        var newUser = new UserModel(dto.login(), dto.password(), dto.role());
        return userRepository.save(newUser);
    }

}
