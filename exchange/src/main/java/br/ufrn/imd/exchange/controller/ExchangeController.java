package br.ufrn.imd.exchange.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final WebClient webClient;

    // URL base da API externa do Currency Freaks
    private static final String API_URL = "https://api.currencyfreaks.com/latest";

    // Chave de API configurada no `application.properties`
    @Value("${currencyfreaks.api.key}")
    private String apiKey;

    // Construtor inicializa o WebClient
    public ExchangeController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    @GetMapping()
    public Mono<Double> exchange() throws Exception {
        
        if (Math.random() < 0.1) {
            throw new RuntimeException();
        }
        return webClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("apikey", apiKey) // Inclui a chave da API na URL
                .queryParam("symbols", "BRL") // Solicita apenas a taxa BRL
                .build())
            .retrieve()
            .bodyToMono(Map.class) // Converte a resposta JSON para um Map
            .map(response -> {
                // Log da resposta completa para debug
                System.out.println("Resposta da API: " + response);

                // Verifica se a resposta possui a chave 'rates'
                if (response == null || !response.containsKey("rates")) {
                    throw new RuntimeException("Resposta inválida da API: 'rates' ausente.");
                }

                Map<?, ?> rates = (Map<?, ?>) response.get("rates");

                // Verifica se a taxa 'BRL' está presente em 'rates'
                if (rates == null || !rates.containsKey("BRL")) {
                    throw new RuntimeException("Resposta inválida da API: 'BRL' ausente.");
                }

                // Converte a taxa de câmbio e calcula o valor em reais
                double taxaCambio = Double.parseDouble((String) rates.get("BRL"));
                //double valorConvertido = value * taxaCambio;
                
                //System.out.println("O valor convertido é R$ " + valorConvertido);
                return taxaCambio;
            })
            .onErrorResume(e -> {
                System.err.println("Erro ao acessar a API externa: " + e.getMessage());
                return Mono.just(0.0); // Retorna 0.0 em caso de erro
            });
    }
}
