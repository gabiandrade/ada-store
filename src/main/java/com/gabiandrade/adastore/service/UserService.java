package com.gabiandrade.adastore.service;

import com.gabiandrade.adastore.dto.RegisterDTO;
import com.gabiandrade.adastore.model.UserEntity;
import com.gabiandrade.adastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveUser(RegisterDTO registerDTO) {
        String passwordEncrypted = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserEntity user = new UserEntity(registerDTO.login(), passwordEncrypted, registerDTO.userRole());
        userRepository.save(user);
    }

}
