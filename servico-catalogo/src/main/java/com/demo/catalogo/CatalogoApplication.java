package com.demo.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class CatalogoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }

    // DTO (Record) simples para o produto
    record Produto(int id, String nome, double preco) {}

    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        // Simula uma busca no banco de dados
        return List.of(
            new Produto(1, "Notebook Java Pro", 7500.00),
            new Produto(2, "Monitor Cloud", 1200.00),
            new Produto(3, "Teclado Microservice", 350.50)
        );
    }
}