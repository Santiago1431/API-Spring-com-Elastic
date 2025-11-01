package com.example.elasticapplication.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(indexName = "produtos")
public class Produtos {

    @Id
    private String Id;

    @Field(type = FieldType.Text, name = "nome")
    private String nome;

    @Field(type = FieldType.Text, name = "marca")
    private String marca;

    @Field(type = FieldType.Integer, name = "preço")
    private int preco;

    @Field(type = FieldType.Nested, name = "distribuidores")
    private List<Distribuidores> distribuidores;
}
