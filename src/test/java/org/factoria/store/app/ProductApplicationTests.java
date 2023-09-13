package org.factoria.store.app;

import org.factoria.store.app.repositories.Product;
import org.factoria.store.app.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductApplicationTests {

    @Autowired
    private TestRestTemplate api;


    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void devuelveLosProductos() {

        List<Product> products = List.of(
                new Product(4L, "Escalera de mano", 45.5),
                new Product(6L, "Fregona", 14.4)
        );

        repository.saveAll(
                products
        );

        var response = api.getForEntity("/api/products", Product[].class);
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(Arrays.stream(response.getBody()).toList(), containsInAnyOrder(products.toArray()));
    }

    @Test
    void guardaUnProducto() {
        Product product = new Product(2356L, "Lavadora", 350.0);

        var response = api.postForEntity("/api/products", product, Product.class);

        assertThat(response.getStatusCode(), equalTo(OK));

        assertThat(repository.findAll(), contains(product));
    }
}


