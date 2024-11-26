package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Atendimento {

    public enum Setor {
        CARTAO,
        EMPRESTIMO,
        OUTROS
    }

    public enum Status{
        ABERTO,
        EM_ATENDIMENTO,
        RESOLVIDO
    }

    public record AtendimentoGetShortDto(
            Long id,
            String titulo,
            String descricao,
            Setor setor,
            Status status) {}

    public record AtendimentoGetFullDto(
            Long id,
            String titulo,
            String descricao,
            Setor setor,
            Cliente.ClienteGetDto cliente,
            Status status,
            Atendente.AtendenteGetShortDto atendente) {}
    public record AtendimentoCreateDto(
            String titulo,
            String descricao,
            Setor setor,
            Long clienteId) {}
    public record AtendimentoEditDto(
            String titulo,
            String descricao,
            Setor setor,
            Long clienteId,
            Status status,
            Long  atendenteId) {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Atendimento.Setor setor;
    @NotNull
    @ManyToOne
    private Cliente cliente;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Atendente atendente;

    public void setStatus(Status status){
        if(status.equals(Status.EM_ATENDIMENTO)){
            throw new IllegalStateException("Não há atendente para iniciar o atendimento");
        }

        this.status = status;
    }

    public AtendimentoGetShortDto getShortDto(){
        return new AtendimentoGetShortDto(
                this.id,
                this.titulo,
                this.descricao,
                this.setor,
                this.status);
    }

    public AtendimentoGetFullDto getFullDto(){
        return new AtendimentoGetFullDto(
                this.id,
                this.titulo,
                this.descricao,
                this.setor,
                this.cliente.getDto(),
                this.status,
                this.atendente != null ? this.atendente.getShortDto() : null);
    }

    public Atendimento(AtendimentoCreateDto createDto, Cliente cliente){
        this.setTitulo(createDto.titulo);
        this.setDescricao(createDto.descricao);
        this.setSetor(createDto.setor);
        this.setCliente(cliente);
        this.setStatus(Status.ABERTO);
    }

    public Atendimento updateUsingDto(AtendimentoEditDto editDto, Cliente cliente){
        this.setTitulo(editDto.titulo);
        this.setDescricao(editDto.descricao);
        this.setSetor(editDto.setor);
        this.setCliente(cliente);
        this.setAtendente(atendente);
        this.setStatus(editDto.status);
        return this;
    }
}
