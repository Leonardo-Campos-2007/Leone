package com.br.leone.entity;

import com.br.leone.enums.StatusAprovacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "perfil_prestador")
public class PerfilPrestador {

    @Id
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    @Column("usuario_id")
    private Long usuarioId;

    @NotBlank(message = "Nome fantasia é obrigatório")
    @Column("nome_fantasia")
    private String nomeFantasia;

    @Column("descricao")
    private String descricao;

    @NotBlank(message = "Área de atuação é obrigatória")
    @Column("area_atuacao")
    private String areaAtuacao;

    @Column("avaliacao_media")
    private Double avaliacaoMedia = 0.0;

    @Column("total_avaliacoes")
    private Integer totalAvaliacoes = 0;

    @Column("status_aprovacao")
    private StatusAprovacao statusAprovacao = StatusAprovacao.PENDENTE;

    @Column("data_solicitacao")
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column("data_aprovacao")
    private LocalDateTime dataAprovacao;

    public PerfilPrestador() {}

    public PerfilPrestador(Long id, Long usuarioId, String nomeFantasia, String descricao,
                           String areaAtuacao, Double avaliacaoMedia, Integer totalAvaliacoes,
                           StatusAprovacao statusAprovacao, LocalDateTime dataSolicitacao,
                           LocalDateTime dataAprovacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nomeFantasia = nomeFantasia;
        this.descricao = descricao;
        this.areaAtuacao = areaAtuacao;
        this.avaliacaoMedia = avaliacaoMedia;
        this.totalAvaliacoes = totalAvaliacoes;
        this.statusAprovacao = statusAprovacao;
        this.dataSolicitacao = dataSolicitacao;
        this.dataAprovacao = dataAprovacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; }

    public Double getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(Double avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }

    public Integer getTotalAvaliacoes() { return totalAvaliacoes; }
    public void setTotalAvaliacoes(Integer totalAvaliacoes) { this.totalAvaliacoes = totalAvaliacoes; }

    public StatusAprovacao getStatusAprovacao() { return statusAprovacao; }
    public void setStatusAprovacao(StatusAprovacao statusAprovacao) { this.statusAprovacao = statusAprovacao; }

    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public LocalDateTime getDataAprovacao() { return dataAprovacao; }
    public void setDataAprovacao(LocalDateTime dataAprovacao) { this.dataAprovacao = dataAprovacao; }
}
