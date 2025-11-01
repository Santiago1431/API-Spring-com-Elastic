package com.example.elasticapplication.repository;

import com.example.elasticapplication.model.Produtos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProdutosRepository extends ElasticsearchRepository<Produtos, String> {
}
