package com.br.leone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "users")
public class User {

    @Id
    private Long id;

    @NotBlank(message = "Nome é obrigatorio")
    @Column("name")
    private String name;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email é obrigatorio")
    @Column("email")
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column("senha")
    private String senha;

    @Size(min = 10, max = 11, message = "Telefone precisa de 10 a 11 digitos")
    @Column("telefone")
    private String telefone;

    @Size(min = 11, max = 11, message = "CPF deve ter 11 digitos")
    @Column("cpf")
    private String cpf;

    public User(){}


    public User(Long id, String name, String email, String senha, String telefone, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
