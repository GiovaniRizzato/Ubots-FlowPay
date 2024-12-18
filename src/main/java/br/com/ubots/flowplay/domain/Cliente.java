package br.com.ubots.flowplay.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.InputMismatchException;

@Data
@Entity
@NoArgsConstructor
public class Cliente {

    public record ClienteGetDto(Long id, String nome, String cpf) {}
    public record ClienteCreateDto(String nome, String cpf) {}
    public record ClienteEditDto(String nome, String cpf) {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(unique=true)
    private String cpf;

    public ClienteGetDto getDto(){
        return new ClienteGetDto(this.id, this.nome, this.cpf);
    }


    public Cliente(ClienteCreateDto createDto){
        this.setNome(createDto.nome);
        this.setCpf(createDto.cpf);
    }

    public Cliente updateUsingDto(ClienteEditDto editDto){
        if(editDto.nome() != null) this.setNome(editDto.nome());
        if(editDto.cpf() != null) this.setCpf(editDto.cpf());
        return this;
    }

    public void setCpf(String cpf) {
        if(!Cliente.isCpfValido(cpf)){
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

    public String getCpf(){
        return Cliente.imprimeCPF(this.cpf);
    }

    public static boolean isCpfValido(String cpf){
        cpf = cpf.replaceAll("[-+.^:,]","");
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere "0" no inteiro 0
                // (48 eh a posicao de "0" na tabela ASCII)
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    private static String imprimeCPF(String cpf) {
        return(cpf.substring(0, 3) + "."
                + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-"
                + cpf.substring(9, 11));
    }
}
