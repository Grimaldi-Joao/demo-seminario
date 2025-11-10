package com.demo.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// Este é o nosso "API Gateway" / BFF
@RestController
class ApiGatewayController {

    private final RestTemplate restTemplate;

    // URLs dos serviços (serão injetadas pelo docker-compose)
    private final String catalogoUrl;
    private final String recomendacoesUrl;

    public ApiGatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        
        // Pega as URLs das variáveis de ambiente definidas no docker-compose
        this.catalogoUrl = System.getenv().getOrDefault("CATALOGO_URL", "http://localhost:8081");
        this.recomendacoesUrl = System.getenv().getOrDefault("RECOMENDACOES_URL", "http://localhost:8082");
    }

    @GetMapping("/api/catalogo")
    public ResponseEntity<String> getCatalogo() {
        // Repassa a chamada para o microserviço de catálogo
        String url = catalogoUrl + "/produtos";
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/api/recomendacoes")
    public ResponseEntity<String> getRecomendacoes() {
        // Repassa a chamada para o microserviço de recomendações
        // **IMPORTANTE**: Este é o ponto que vai falhar na Demo 2
        String url = recomendacoesUrl + "/recomendacoes";
        return restTemplate.getForEntity(url, String.class);
    }
}