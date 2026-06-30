package com.br.leone.service;

import com.br.leone.dto.UserRequestDTO;
import com.br.leone.dto.UserResponseDTO;
import com.br.leone.entity.User;
import com.br.leone.enums.Role;
import com.br.leone.enums.TipoConta;
import com.br.leone.exception.DadosJaCadastradosException;
import com.br.leone.exception.UsuarioNaoEncontradoException;
import com.br.leone.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public UserResponseDTO criar(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new DadosJaCadastradosException();
        }

        validarDocumentos(dto);

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setSenha(passwordEncoder.encode(dto.senha()));
        user.setTelefone(dto.telefone());
        user.setCpf(dto.cpf());
        user.setCnpj(dto.cnpj());
        user.setTipoConta(dto.tipoConta());
        user.setRole(Role.COMPRADOR);
        user.setDataCadastro(LocalDateTime.now());

        return toDTO(userRepository.save(user));
    }

    public List<UserResponseDTO> listarTodos() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<UserResponseDTO> buscarPorId(Long id) {
        return userRepository.findById(id).map(this::toDTO);
    }

    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponseDTO atualizar(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        user.setName(dto.name());
        user.setTelefone(dto.telefone());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            user.setSenha(passwordEncoder.encode(dto.senha()));
        }

        return toDTO(userRepository.save(user));
    }

    public void deletar(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException(id);
        }
        userRepository.deleteById(id);
    }

    private void validarDocumentos(UserRequestDTO dto) {
        if (dto.tipoConta() == TipoConta.PESSOA_FISICA) {
            if (dto.cpf() == null || dto.cpf().isBlank()) {
                throw new IllegalArgumentException("CPF é obrigatório para Pessoa Física.");
            }
        }
        if (dto.tipoConta() == TipoConta.PESSOA_JURIDICA) {
            if (dto.cnpj() == null || dto.cnpj().isBlank()) {
                throw new IllegalArgumentException("CNPJ é obrigatório para Pessoa Jurídica.");
            }
        }
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTelefone(),
                user.getCpf(),
                user.getCnpj(),
                user.getTipoConta(),
                user.getRole(),
                user.getDataCadastro()
        );
    }
}
