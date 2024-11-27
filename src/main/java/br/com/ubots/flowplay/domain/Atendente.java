package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class Atendente {

    public record AtendenteGetShortDto(Long id, String nome, Atendimento.Setor setor) {}
    public record AtendenteGetFullDto(Long id, String nome, Atendimento.Setor setor, Set<Atendimento.AtendimentoGetShortDto> atendimentos) {}
    public record AtendenteCreateDto(String nome, Atendimento.Setor setor) {}
    public record AtendenteEditDto(String nome, Atendimento.Setor setor) {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @NotNull
    private String nome;

    @NotNull
    private Atendimento.Setor setor;

    @OneToMany(mappedBy = "atendente")
    private List<Atendimento> atendimentos;

    private void setAtendimento(Set<Atendimento> atendimentos){
        //Setando o metodo de "set" para evitar de ser alterado fora desta classe,
        //O atendimento é iniciado atravez de edição da classe "Atendimento"
    }

    public AtendenteGetShortDto getShortDto(){
        return new AtendenteGetShortDto(this.id, this.nome, this.setor);
    }

    public AtendenteGetFullDto getFullDto(){
        return new AtendenteGetFullDto(
                this.id,
                this.nome,
                this.setor,
                this.atendimentos != null ? this.atendimentos.stream()
                        .map(Atendimento::getShortDto)
                        .collect(Collectors.toSet()) : null);
    }

    public Atendente(AtendenteCreateDto createDto){
        this.setNome(createDto.nome);
        this.setSetor(createDto.setor);
    }

    public Atendente updateUsingDto(AtendenteEditDto editDto){
        if(editDto.nome() != null) this.setNome(editDto.nome());
        if(editDto.setor() != null) this.setSetor(editDto.setor());
        return this;
    }
}
