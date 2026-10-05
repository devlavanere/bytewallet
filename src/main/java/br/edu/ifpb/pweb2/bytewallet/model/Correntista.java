package br.edu.ifpb.pweb2.bytewallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;

@Data // Gera todos os Getters e Setters automaticamente
@Entity
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "CPF inválido. Verifique os números digitados.")
    private String cpf;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser no passado.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private boolean bloqueado = false;

    @Transient // Avisa ao banco (PostgreSQL) para NÃO tentar criar uma coluna
    public Integer getIdade() {
        if (this.dataNascimento != null) {
            // Calcula a diferença em anos entre a data de nascimento e a data de hoje
            return Period.between(this.dataNascimento, LocalDate.now()).getYears();
        }
        return 0;
    }
}