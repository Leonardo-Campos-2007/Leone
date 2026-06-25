package com.br.leone.service;

import com.br.leone.entity.User;
import com.br.leone.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User criar(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public Optional<User> buscarPorId(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User atualizar (Long id, User dadosNovos){
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        userExistente.setName(dadosNovos.getName());
        userExistente.setTelefone(dadosNovos.getTelefone());

        if (dadosNovos.getSenha() != null && !dadosNovos.getSenha().isBlank()){
            userExistente.setSenha(passwordEncoder.encode(dadosNovos.getSenha()));
        }

        return userRepository.save(userExistente);
    }

    public void deletar(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        userRepository.existsById(id);
    }

}
