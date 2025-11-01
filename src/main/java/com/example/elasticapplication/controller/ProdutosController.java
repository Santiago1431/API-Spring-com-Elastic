package com.example.elasticapplication.controller;

import com.example.elasticapplication.model.Produtos;
import com.example.elasticapplication.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private final ProdutosRepository produtosRepository;

    @PostMapping
    public void addProduto(@RequestBody Produtos produto){
        produtosRepository.save(produto);
    }

    @GetMapping("/{id}")
    public Produtos findById(@PathVariable String id){
        return produtosRepository.findById(id).get();
    }

    @GetMapping
    public Iterable<Produtos> findAll(){
        return produtosRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        produtosRepository.deleteById(id);
    }

    @PutMapping
    public void atualizarProduto(@RequestBody Produtos produto){
        produtosRepository.save(produto);
    }


}
