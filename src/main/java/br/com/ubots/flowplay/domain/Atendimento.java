package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Atendimento {

    public enum Assunto{
        CARTAO,
        EMPRESTIMO,
        OUTROS
    }

    public record AtendimentoGetDto(Long id, String titulo, String descricao, Assunto assunto, Cliente.ClienteGetDto cliente) {}
    public record AtendimentoCreateDto(String titulo, String descricao, Assunto assunto, Long clienteId) {}
    public record AtendimentoEditDto(String titulo, String descricao, Assunto assunto, Long clienteId) {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Assunto assunto;
    @NotNull
    @ManyToOne
    private Cliente cliente;

    public AtendimentoGetDto getDto(){
        return new AtendimentoGetDto(
                this.id,
                this.titulo,
                this.descricao,
                this.assunto,
                this.cliente.getDto());
    }

    public Atendimento(AtendimentoCreateDto createDto, Cliente cliente){
        this.setTitulo(createDto.titulo);
        this.setDescricao(createDto.descricao);
        this.setAssunto(createDto.assunto);
        this.setCliente(cliente);
    }

    public Atendimento updateUsingDto(AtendimentoEditDto editDto, Cliente cliente){
        this.setTitulo(editDto.titulo);
        this.setDescricao(editDto.descricao);
        this.setAssunto(editDto.assunto);
        this.setCliente(cliente);
        return this;
    }
}
