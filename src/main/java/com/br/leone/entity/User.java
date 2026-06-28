package com.br.leone.entity;

import com.br.leone.enums.Role;
import com.br.leone.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "users")
public class User {

    @Id
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column("name")
    private String name;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    @Column("email")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column("senha")
    private String senha;

    @Size(min = 10, max = 11, message = "Telefone deve ter entre 10 e 11 dígitos")
    @Column("telefone")
    private String telefone;

    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @Column("cpf")
    private String cpf;

    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
    @Column("cnpj")
    private String cnpj;

    @NotNull(message = "Tipo de conta é obrigatório")
    @Column("tipo_conta")
    private TipoConta tipoConta;

    @Column("role")
    private Role role = Role.COMPRADOR;

    @Column("data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public User() {}

    public User(Long id, String name, String email, String senha, String telefone,
                String cpf, String cnpj, TipoConta tipoConta, Role role, LocalDateTime dataCadastro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoConta = tipoConta;
        this.role = role;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public TipoConta getTipoConta() { return tipoConta; }
    public void setTipoConta(TipoConta tipoConta) { this.tipoConta = tipoConta; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}
