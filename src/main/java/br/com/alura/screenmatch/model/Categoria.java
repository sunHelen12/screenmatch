package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    ANIMATION ("Animation"),
    AVENTURA("Adventure"),
    CRIME("Crime");

   private String categoriaOmdb;

   Categoria (String categoriaOmdb){
    this.categoriaOmdb = categoriaOmdb;
   }

   public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
