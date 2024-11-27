package br.com.ubots.flowplay.repository;

import br.com.ubots.flowplay.domain.Atendente;
import br.com.ubots.flowplay.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAllBySetorAndStatusOrderByCriadoEmAsc(Atendimento.Setor setor, Atendimento.Status status);

    Collection<Atendimento> findAllByAtendente(Atendente atendente);
}