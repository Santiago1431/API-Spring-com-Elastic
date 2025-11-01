package com.example.elasticapplication.controller;

import com.example.elasticapplication.model.Produtos;
// Importe o serviço
import com.example.elasticapplication.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    // Altere do Repository para o Service
    private final ProdutoService produtoService;

    @PostMapping
    public void addProduto(@RequestBody Produtos produto){
        produtoService.addProduto(produto); // use o serviço
    }

    @GetMapping("/{id}")
    public Produtos findById(@PathVariable String id){
        return produtoService.findById(id); // use o serviço
    }

    @GetMapping
    public Iterable<Produtos> findAll(){
        return produtoService.findAll(); // use o serviço
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        produtoService.delete(id); // use o serviço
    }

    @PutMapping
    public void atualizarProduto(@RequestBody Produtos produto){
        produtoService.atualizarProduto(produto); // use o serviço
    }

    // --- NOSSO NOVO ENDPOINT DE BUSCA AVANÇADA ---

    @GetMapping("/search")
    public List<Produtos> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer precoMin,
            @RequestParam(required = false) Integer precoMax
    ) {
        return produtoService.buscaAvancada(nome, marca, precoMin, precoMax);
    }
}