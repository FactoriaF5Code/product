package org.factoria.store.app;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestDeAceptacionDePrueba {

    @Autowired
    private TestRestTemplate api;

    @Test
    void laApiDiceHola() {

        var response = api.getForEntity("/api/hola", String.class);

        assertThat(response.getBody(), equalTo("Hola Javi!"));


    }
}
