package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class Atendente {

    public record AtendenteGetShortDto(Long id, String nome, Atendimento.Setor setor) {}
    public record AtendenteGetFullDto(Long id, String nome, Atendimento.Setor setor, Set<Atendimento.AtendimentoGetShortDto> atendimentos) {}
    public record AtendenteCreateDto(String nome, Atendimento.Setor setor) {}
    public record AtendenteEditDto(String nome, Atendimento.Setor setor, Long[] atendimentos) {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @NotNull
    private String nome;

    @NotNull
    private Atendimento.Setor setor;

    @OneToMany
    private Set<Atendimento> atendimentos = new HashSet<>();

    public AtendenteGetShortDto getShortDto(){
        return new AtendenteGetShortDto(this.id, this.nome, this.setor);
    }

    public AtendenteGetFullDto getFullDto(){
        return new AtendenteGetFullDto(
                this.id,
                this.nome,
                this.setor,
                this.atendimentos.stream()
                        .map(Atendimento::getShortDto)
                        .collect(Collectors.toSet()));
    }


    public Atendente(AtendenteCreateDto createDto){
        this.setNome(createDto.nome);
        this.setSetor(createDto.setor);
    }

    public Atendente updateUsingDto(AtendenteEditDto editDto){
        this.setNome(editDto.nome);
        this.setSetor(editDto.setor);
        return this;
    }
}
