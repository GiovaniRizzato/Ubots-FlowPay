package br.com.ubots.flowplay.service;

import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Autowired
    private ClienteService clienteService;

    public Collection<Atendimento> getAll() {
        return this.repository.findAll();
    }

    public Collection<Atendimento> getAllBySetor(Atendimento.Setor setor) {
        return this.repository.findAllBySetor(setor);
    }

    public Atendimento getById(Long id) throws NoSuchElementException {
        return this.repository.findById(id).orElseThrow();
    }

    public Atendimento create(Atendimento.AtendimentoCreateDto createDTO) throws NoSuchElementException {
        return this.repository.save(new Atendimento(createDTO, clienteService.getById(createDTO.clienteId())));
    }

    public Atendimento edit(Long id, Atendimento.AtendimentoEditDto editDto) throws NoSuchElementException {
        return this.repository.save(this.getById(id).updateUsingDto(editDto, clienteService.getById(editDto.clienteId())));
    }

    public void remove(Long id) throws NoSuchElementException {
        this.repository.delete(this.getById(id));
    }
}
