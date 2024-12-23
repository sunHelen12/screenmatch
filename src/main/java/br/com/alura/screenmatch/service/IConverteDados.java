package br.com.alura.screenmatch.service;

public interface IConverteDados {
   <T> T obterDados(String json, Class<T> classe);

   //<T> T obterDados(String json) -> eu não sei o que irá retornar, mas deve-se retornar alguma coisa (generics)
}
