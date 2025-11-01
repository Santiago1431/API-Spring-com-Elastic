package com.example.elasticapplication.model;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Distribuidores {

    @Field(type = FieldType.Text, name = "nome")
    private String nome;

    @Field(type = FieldType.Boolean, name = "é Ativo")
    private boolean ativo;
}
