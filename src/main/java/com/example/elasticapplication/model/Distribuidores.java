package com.example.elasticapplication.model;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Data
public class Distribuidores {

    @MultiField(
            mainField = @Field(type = FieldType.Text, name = "nome"), // O campo principal 'nome' será do tipo Text
            otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword) // Um sub-campo 'nome.keyword' será criado
    )
    private String nome;

    @Field(type = FieldType.Boolean, name = "ativo")
    private boolean ativo;
}
