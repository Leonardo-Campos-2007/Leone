package com.br.leone.controller;


import com.br.leone.entity.User;
import com.br.leone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController  {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> criarUsuario(@RequestBody User user) {
        User novoUsuario = userService.criar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<User>> listarTodos(){
        List<User> usuarios = userService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> buscarPorId(@PathVariable Long id) {
        Optional<User> usuario = userService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Long id,  @RequestBody User userAtualizado) {
        User usuarioDefinitivo = userService.atualizar(id, userAtualizado);
        return ResponseEntity.ok(usuarioDefinitivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
