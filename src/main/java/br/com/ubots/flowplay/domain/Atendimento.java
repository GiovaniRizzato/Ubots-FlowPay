package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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

    @NotNull
    private LocalDate criadoEm;

    private void setCriadoEm(LocalDate newDate){
        //Setando o metodo de "set" para evitar de ser alterado fora desta classe
        this.criadoEm = newDate;
    }

    public void setAtendente(Atendente atendente) throws IllegalStateException{
        if(!this.setor.equals(atendente.getSetor())){
            throw new IllegalStateException("Atendente não está no mesmo setor que este atendimento");
        }

        this.status = Status.EM_ATENDIMENTO;
        this.atendente = atendente;
    }

    private void setStatus(Status status) throws IllegalStateException, IllegalAccessException {
        if (this.status != null) {
            switch (this.status) {
                case ABERTO -> {
                    if(status.equals(Status.EM_ATENDIMENTO) && this.atendente == null){
                        throw new IllegalStateException("É necessario um atendente para dar andmaneto ao atendimento");
                    }
                }
                case EM_ATENDIMENTO -> {
                    if (!status.equals(Status.EM_ATENDIMENTO)) {
                        //Quando alterar o estado, o atendente DEVE voltar "não estar atendendo"
                        this.atendente = null;
                    }
                }
                case RESOLVIDO -> throw new IllegalAccessException("Atendimento resolvido");
            }
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

    public Atendimento(AtendimentoCreateDto createDto, Cliente cliente) throws IllegalAccessException {
        this.setTitulo(createDto.titulo);
        this.setDescricao(createDto.descricao);
        this.setSetor(createDto.setor);
        this.setCliente(cliente);
        this.setStatus(Status.ABERTO);
        this.setCriadoEm(LocalDate.now());
    }

    public Atendimento updateUsingDto(AtendimentoEditDto editDto, Cliente cliente, Atendente atendente) throws IllegalAccessException {
        this.setTitulo(editDto.titulo);
        this.setDescricao(editDto.descricao);
        this.setSetor(editDto.setor);
        this.setStatus(editDto.status);
        if(cliente != null) this.setCliente(cliente);
        if(atendente != null) this.setAtendente(atendente);
        return this;
    }
}
