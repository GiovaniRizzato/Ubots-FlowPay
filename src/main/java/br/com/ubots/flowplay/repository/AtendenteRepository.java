package br.com.ubots.flowplay.repository;

import br.com.ubots.flowplay.domain.Atendente;
import br.com.ubots.flowplay.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    @Query("SELECT a FROM Atendente a WHERE a.setor = ?1 AND SIZE(a.atendimentos) <= ?2")
    Collection<Atendente> findAllAvalibleBySetor(Atendimento.Setor setor, int qtd_max);
}