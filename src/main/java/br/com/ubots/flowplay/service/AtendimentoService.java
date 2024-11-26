package br.com.ubots.flowplay.service;

import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Autowired
    private ClienteService clienteService;

    public Collection<Atendimento> getAll() {
        return this.repository.findAll();
    }

    public Collection<Atendimento> getAllByAssunto(Atendimento.Assunto assunto) {
        return this.repository.findAllByAssunto(assunto);
    }

    public Atendimento getById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Atendimento create(Atendimento.AtendimentoCreateDto createDTO) {
        return this.repository.save(new Atendimento(createDTO, clienteService.getById(createDTO.clienteId())));
    }

    public Atendimento edit(Long id, Atendimento.AtendimentoEditDto editDto) {
        return this.repository.save(this.getById(id).updateUsingDto(editDto, clienteService.getById(editDto.clienteId())));
    }

    public void remove(Long id) {
        this.repository.delete(this.getById(id));
    }
}
