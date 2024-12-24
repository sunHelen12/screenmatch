package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporadas;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {
    
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2e70c955";

    public void exibeMenu(){
        System.out.print("Digite o nome da série para busca: ");
        var nomeSerie = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.toLowerCase().replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        //json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);

        //var consumoApi = new ConsumoApi(); 
        
        List<DadosTemporadas> temporadas = new ArrayList<>();
        
        for (int i = 1; i<=dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
        /*
        for (int i = 0; i < dados.totalTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporadas.size(); j++) {
                System.out.println(episodiosTemporadas.get(j).titulo());
            }
            
        }
        */
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        
        //ordena os nomes gerando um novo fluxo de dados :
        /*
        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico"); //nomes = fluxo de dados
        nomes.stream()
            .sorted()//operacoes intermediarias gera novo fluxo de dados
            .limit(3)
            .filter(n -> n.startsWith("N"))
            .map(n -> n.toUpperCase())
            .forEach(System.out::println); //isso pode ser colocado em apenas uma linha
        */
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
            .flatMap(t -> t.episodios().stream())
            .collect(Collectors.toList()); // se quiser alterar a lista
//          .toList(); // se não quiser alterar a lista
        System.out.println("\nTop 5 episódios:");
        dadosEpisodios.stream()
        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")) // "quero encontrar todo mundo que não seja igua a "N/A"
            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);

        List<Episodio> episodios = new ArrayList<>();
    }
}
