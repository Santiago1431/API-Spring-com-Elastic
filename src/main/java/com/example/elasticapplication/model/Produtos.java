package com.example.elasticapplication.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

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

    @MultiField(
            mainField = @Field(type = FieldType.Text, name = "marca"), // O campo principal 'marca' será do tipo Text
            otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword) // Um sub-campo 'marca.keyword' será criado
    )
    private String marca;

    @Field(type = FieldType.Integer, name = "preco")
    private int preco;

    @Field(type = FieldType.Nested, name = "distribuidores")
    private List<Distribuidores> distribuidores;
}
