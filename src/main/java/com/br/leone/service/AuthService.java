package com.br.leone.service;

import com.br.leone.dto.LoginRequest;
import com.br.leone.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

   public User autenticar(LoginRequest request) {
        User user = userService.buscarPorEmail(request.email())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(request.senha(), user.getSenha())) {
            throw new RuntimeException("E-mail ou senha invalidos");
        }
        return user;
   }
}
