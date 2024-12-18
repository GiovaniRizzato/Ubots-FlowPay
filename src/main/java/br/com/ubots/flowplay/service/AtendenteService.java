package br.com.ubots.flowplay.service;

import br.com.ubots.flowplay.domain.Atendente;
import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.repository.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AtendenteService {

    final public static Integer MAX_ATENDIMENTOS_POR_ATENDENTE = 3;

    @Autowired
    private AtendenteRepository repository;

    public Collection<Atendente> getAll() {
        return this.repository.findAll();
    }

    public Collection<Atendente> getAllAvalible(Atendimento.Setor setor) {
        return this.repository.findAllAvalibleBySetor(setor, AtendenteService.MAX_ATENDIMENTOS_POR_ATENDENTE);
    }

    public Atendente getById(Long id) throws NoSuchElementException {
        return this.repository.findById(id).orElseThrow();
    }

    public Atendente create(Atendente.AtendenteCreateDto createDTO) throws NoSuchElementException {
        return this.repository.save(new Atendente(createDTO));
    }

    public Atendente edit(Long id, Atendente.AtendenteEditDto editDto) throws NoSuchElementException {
        final Atendente atendente = this.getById(id).updateUsingDto(editDto);
        return this.repository.save(atendente);
    }

    public void remove(Long id) throws NoSuchElementException {
        this.repository.delete(this.getById(id));
    }
}
