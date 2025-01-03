package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    //@Column(name = "nomeDaSerie") poderiamos trocar o nome da variável no banco de dados
    @Column(unique = true, length = 500)//para não repetir titulos de series    
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero; // Categoria é um enum
    @Column(length = 500)
    private String atores;
    private String poster;
    @Column(length = 5000)
    private String sinopse;
    //@Transient não vai mexer nos episodios por enquanto
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();
   
    public Serie(){}

    public Serie(DadosSerie dadosSerie){
            this.titulo = dadosSerie.titulo();
            this.totalTemporadas = dadosSerie.totalTemporadas();
            this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
            this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
            this.atores = dadosSerie.atores();
            this.poster = dadosSerie.poster();
            this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
        }
    

    // Getters e Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return "\nGênero= " + genero + "\n" +
           "Título= " + titulo + "\n" +
           "Total de Temporadas= " + totalTemporadas + "\n" +
           "Avaliação= " + avaliacao + "\n" +
           "Atores= " + atores + "\n" +
           "Pôster= " + poster + "\n" +
           "Sinopse= " + sinopse + "\n" +
           "Episódios= " + episodios;
    }
}
