package com.example.elasticapplication.service;

import com.example.elasticapplication.model.Produtos;
import com.example.elasticapplication.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

// Importações do Cliente Java v8
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
// Removida a importação desnecessária do JsonData
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.NumberRangeQuery;

// Imports do Spring Data
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutosRepository produtosRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ProdutoServiceImpl(ProdutosRepository produtosRepository, ElasticsearchOperations elasticsearchOperations) {
        this.produtosRepository = produtosRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    // --- Métodos CRUD (sem alteração) ---
    @Override
    public void addProduto(Produtos produto) { produtosRepository.save(produto); }
    @Override
    public Produtos findById(String id) { return produtosRepository.findById(id).orElse(null); }
    @Override
    public Iterable<Produtos> findAll() { return produtosRepository.findAll(); }
    @Override
    public void delete(String id) { produtosRepository.deleteById(id); }
    @Override
    public void atualizarProduto(Produtos produto) { produtosRepository.save(produto); }

    // --- Busca Avançada (COM A CORREÇÃO DE TIPO) ---
    @Override
    public List<Produtos> buscaAvancada(String nome, String marca, Integer precoMin, Integer precoMax) {

        var boolQuery = new BoolQuery.Builder();

        // 1. Full-Text Search no 'nome' (sem alteração)
        if (nome != null && !nome.isEmpty()) {
            boolQuery.must(q -> q
                    .match(m -> m
                            .field("nome")
                            .query(nome)
                    )
            );
        }

        // 2. Filtro (exato) no 'marca' (sem alteração)
        if (marca != null && !marca.isEmpty()) {
            boolQuery.filter(f -> f
                    .term(t -> t
                            .field("marca.keyword")
                            .value(marca)
                    )
            );
        }

        // --- 3. Filtro de Faixa (Range) no 'preco' (CORREÇÃO DE TIPO) ---
        if (precoMin != null || precoMax != null) {

            var rangeQueryBuilder = new NumberRangeQuery.Builder()
                    .field("preco"); // Usando "preco" (sem 'ç')

            if (precoMin != null) {
                // Converta Integer para Double
                rangeQueryBuilder.gte(precoMin.doubleValue());
            }
            if (precoMax != null) {
                // Converta Integer para Double
                rangeQueryBuilder.lte(precoMax.doubleValue());
            }

            Query rangeQuery = rangeQueryBuilder.build()._toRangeQuery()._toQuery();
            boolQuery.filter(rangeQuery);
        }

        // Construímos a Query Nativa (sem alteração)
        org.springframework.data.elasticsearch.core.query.Query query = NativeQuery.builder()
                .withQuery(boolQuery.build()._toQuery())
                .build();

        // Executamos a busca (sem alteração)
        SearchHits<Produtos> searchHits = elasticsearchOperations.search(query, Produtos.class);

        // Mapeamos os resultados (sem alteração)
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}