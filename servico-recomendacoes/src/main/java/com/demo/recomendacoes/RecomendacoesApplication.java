package com.demo.recomendacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class RecomendacoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecomendacoesApplication.class, args);
    }

    // DTO
    record Recomendacao(int id, String nome) {}

    @GetMapping("/recomendacoes")
    public List<Recomendacao> getRecomendacoes() {
        return List.of(
            new Recomendacao(101, "Livro 'Entendendo Kubernetes'"),
            new Recomendacao(102, "Curso de Docker")
        );
    }
}