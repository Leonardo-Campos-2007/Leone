package com.br.leone.repository;

import com.br.leone.entity.CategoriaServico;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaServicoRepository extends ListCrudRepository<CategoriaServico, Long> {

    List<CategoriaServico> findByCategoriaPaiId(Long categoriaPaiId);

    List<CategoriaServico> findByCategoriaPaiIdIsNull();

    boolean existsByNomeAndCategoriaPaiId(String nome, Long categoriaPaiId);
}
