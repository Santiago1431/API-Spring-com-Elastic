package com.example.elasticapplication.service;

import com.example.elasticapplication.model.Produtos;
import java.util.List;

public interface ProdutoService {
    void addProduto(Produtos produto);

    Produtos findById(String id);

    Iterable<Produtos> findAll();

    void delete(String id);

    void atualizarProduto(Produtos produto);

    // Nosso novo método de busca avançada
    List<Produtos> buscaAvancada(String nome, String marca, Integer precoMin, Integer precoMax);
}
