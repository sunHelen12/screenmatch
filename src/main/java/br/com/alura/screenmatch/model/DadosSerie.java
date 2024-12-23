package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //ignora o que eu não solicitei, o que não encontrar
public record DadosSerie(@JsonAlias("Title")String titulo, 
                        @JsonAlias("totalSeasons") Integer totalTemporadas, 
                        @JsonAlias("imdbRating") String avaliacao, 
                        @JsonProperty("imdbVotes")String votes){

    //JsonAlias serve para referenciarmos uma variável a um tópico do Json ex: title = titulo
    //JsonProperty("imdbVote) faz os dois caminhos;
}
