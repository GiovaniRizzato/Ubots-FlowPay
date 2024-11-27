package br.com.ubots.flowplay.service;

import br.com.ubots.flowplay.domain.Atendente;
import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.domain.Cliente;
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

    @Autowired
    private AtendenteService atendenteService;

    public Collection<Atendimento> getAll() {
        return this.repository.findAll();
    }

    public Collection<Atendimento> getAllBySetor(Atendimento.Setor setor) {
        return this.repository.findAllBySetorAndStatusOrderByCriadoEmAsc(setor, Atendimento.Status.ABERTO);
    }

    public Collection<Atendimento> getAllByAtendente(Long atendenteId) throws NoSuchElementException {
        return this.repository.findAllByAtendente(this.atendenteService.getById(atendenteId));
    }

    public Atendimento getById(Long id) throws NoSuchElementException {
        return this.repository.findById(id).orElseThrow();
    }

    public Atendimento create(Atendimento.AtendimentoCreateDto createDTO) throws NoSuchElementException, IllegalAccessException {
        return this.repository.save(new Atendimento(createDTO, this.clienteService.getById(createDTO.clienteId())));
    }

    public Atendimento edit(Long id, Atendimento.AtendimentoEditDto editDto) throws NoSuchElementException, IllegalAccessException {
        final Cliente cliente = editDto.clienteId() != null ? this.clienteService.getById(editDto.clienteId()) : null;
        final Atendente atendente = editDto.atendenteId() != null ? this.atendenteService.getById(editDto.atendenteId()) : null;
        return this.repository.save(this.getById(id).updateUsingDto(editDto, cliente, atendente));
    }

    public void remove(Long id) throws NoSuchElementException {
        this.repository.delete(this.getById(id));
    }
}
