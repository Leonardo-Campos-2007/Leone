package com.br.leone.service;

import com.br.leone.entity.CategoriaServico;
import com.br.leone.enums.StatusAprovacao;
import com.br.leone.exception.CategoriaJaCadastradaException;
import com.br.leone.exception.CategoriaNaoEncontradaException;
import com.br.leone.repository.CategoriaServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicoService {

    private final CategoriaServicoRepository categoriaServicoRepository;

    public CategoriaServicoService(CategoriaServicoRepository categoriaServicoRepository) {
        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    public CategoriaServico criar(CategoriaServico categoria) {
        validarPaiENome(categoria);
        categoria.setStatusAprovacao(StatusAprovacao.APROVADO);
        categoria.setCriadoPorUsuarioId(null);
        return categoriaServicoRepository.save(categoria);
    }

    public CategoriaServico sugerir(CategoriaServico categoria, Long usuarioId) {
        validarPaiENome(categoria);
        categoria.setStatusAprovacao(StatusAprovacao.PENDENTE);
        categoria.setCriadoPorUsuarioId(usuarioId);
        return categoriaServicoRepository.save(categoria);
    }

    public CategoriaServico aprovarSugestao(Long id) {
        CategoriaServico categoria = categoriaServicoRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        categoria.setStatusAprovacao(StatusAprovacao.APROVADO);
        return categoriaServicoRepository.save(categoria);
    }

    public void rejeitarSugestao(Long id) {
        if (!categoriaServicoRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException(id);
        }
        categoriaServicoRepository.deleteById(id);
    }

    public List<CategoriaServico> listarPendentes() {
        return categoriaServicoRepository.findByStatusAprovacao(StatusAprovacao.PENDENTE);
    }

    public List<CategoriaServico> listarTodos() {
        return categoriaServicoRepository.findAll();
    }

    public List<CategoriaServico> listarRaizes() {
        return categoriaServicoRepository.findByCategoriaPaiIdIsNull();
    }

    public List<CategoriaServico> listarSubcategorias(Long categoriaPaiId) {
        return categoriaServicoRepository.findByCategoriaPaiId(categoriaPaiId);
    }

    public Optional<CategoriaServico> buscarPorId(Long id) {
        return categoriaServicoRepository.findById(id);
    }

    public CategoriaServico atualizar(Long id, CategoriaServico dadosNovos) {
        CategoriaServico categoriaExistente = categoriaServicoRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        categoriaExistente.setNome(dadosNovos.getNome());
        categoriaExistente.setDescricao(dadosNovos.getDescricao());

        return categoriaServicoRepository.save(categoriaExistente);
    }

    public void deletar(Long id) {
        if (!categoriaServicoRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException(id);
        }
        categoriaServicoRepository.deleteById(id);
    }

    private void validarPaiENome(CategoriaServico categoria) {
        if (categoria.getCategoriaPaiId() != null) {
            categoriaServicoRepository.findById(categoria.getCategoriaPaiId())
                    .orElseThrow(() -> new CategoriaNaoEncontradaException(categoria.getCategoriaPaiId()));
        }
        if (categoriaServicoRepository.existsByNomeAndCategoriaPaiId(
                categoria.getNome(), categoria.getCategoriaPaiId())) {
            throw new CategoriaJaCadastradaException();
        }
    }
}