package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Marcos vinicius");
        cliente1.setTelefone("44991992300");
        cliente1.setEmail("marcosvinicius@gmail.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Vaniele");
        cliente2.setTelefone("44991990722");
        cliente2.setEmail("vaniele@gmail.com");

        return Arrays.asList(cliente1, cliente2);
    }
}