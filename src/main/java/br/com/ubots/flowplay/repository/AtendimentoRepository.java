package br.com.ubots.flowplay.repository;

import br.com.ubots.flowplay.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    Collection<Atendimento> findAllByAssunto(Atendimento.Assunto assunto);
}