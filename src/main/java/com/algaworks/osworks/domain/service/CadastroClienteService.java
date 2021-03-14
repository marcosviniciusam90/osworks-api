package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe que mantém regras de negócio associadas ao cadastro de clientes
 */
@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    public boolean existsById(Long clienteId) {
        return clienteRepository.existsById(clienteId);
    }

    public Cliente save(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com este email" +
                    " (em produção não faça isso, deixa o sistema vulnerável");
        }

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
