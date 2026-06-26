package com.br.leone.controller;


import com.br.leone.entity.User;
import com.br.leone.exception.UsuarioNaoEncontradoException;
import com.br.leone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController  {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> criarUsuario(@Valid @RequestBody User user) {
        User novoUsuario = userService.criar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<User>> listarTodos(){
        List<User> usuarios = userService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody User userAtualizado) {
        User usuarioDefinitivo = userService.atualizar(id, userAtualizado);
        return ResponseEntity.ok(usuarioDefinitivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
