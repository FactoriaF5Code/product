package org.factoria.store.app;

import org.factoria.store.app.repositories.Product;
import org.factoria.store.app.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class ProductApplicationTestsWithMockMvc {

    @Autowired
    private MockMvc api;


    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void devuelveLosProductos() throws Exception {

        List<Product> products = List.of(
                new Product(4L, "Escalera de mano", 45.5),
                new Product(6L, "Fregona", 14.4)
        );

        repository.saveAll(
                products
        );

        api.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Escalera de mano")))
                .andExpect(jsonPath("$[0].price", is(45.5)))
                .andExpect(jsonPath("$[1].name", is("Fregona")))
                .andExpect(jsonPath("$[1].price", is(14.4)));
    }

    @Test
    void guardaUnProducto() throws Exception {
        Product product = new Product(2356L, "Lavadora", 350.0);

        api.perform(post("/api/products")
                .content("{ \"id\": 2356, \"name\": \"Lavadora\", \"price\": 350.0}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        assertThat(repository.findAll(), contains(product));

    }
}


