package org.factoria.store.app;

import org.factoria.store.app.repositories.Product;
import org.factoria.store.app.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestDeAceptacion {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestRestTemplate api;

    @Test
    public void devuelveLosProductos() {
        List<Product> products = List.of(
                new Product(1L, "Lavadora", 300.50f),
                new Product(2L, "Nevera", 700.00f)
        );

        repository.saveAll(products);

        // lo mismo que Postman
        var response = api.getForEntity("/api/products", Product[].class);

        // comprueba que la respuesta es 200 (OK)
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        // assertThat(LA LISTA QUE ME DEVUELVE ESTO ES LA QUE YO TENIA AL PRINCIPIO)
        assertThat(Arrays.stream(response.getBody()).toList(),
                containsInAnyOrder(products.toArray()));

    }
}
