package br.com.ubots.flowplay.service;

import br.com.ubots.flowplay.domain.Cliente;
import br.com.ubots.flowplay.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService{

    @Autowired
    private ClienteRepository repository;

    public Collection<Cliente> getAll() {
        return this.repository.findAll();
    }

    public Cliente getById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Cliente create(Cliente.CreateDto createDTO) {
        return this.repository.save(new Cliente(createDTO));
    }

    public Cliente edit(Long id, Cliente.EditDto editDto) {
        return this.repository.save(this.getById(id).updateUsingDto(editDto));
    }

    public void remove(Long id) {
        this.repository.delete(this.getById(id));
    }
}
