package com.br.leone.entity;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "categoria_servico")
public class CategoriaServico {

    @Id
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column("nome")
    private String nome;

    @Column("descricao")
    private String descricao;

    @Column("categoria_pai_id")
    private Long categoriaPaiId;

    public CategoriaServico() {}

    public CategoriaServico(Long id, String nome, String descricao, Long categoriaPaiId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaPaiId = categoriaPaiId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getCategoriaPaiId() { return categoriaPaiId; }
    public void setCategoriaPaiId(Long categoriaPaiId) { this.categoriaPaiId = categoriaPaiId; }
}