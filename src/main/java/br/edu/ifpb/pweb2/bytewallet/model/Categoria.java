package br.edu.ifpb.pweb2.bytewallet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private boolean ativo = true;

    private String natureza; // Entrada, Saida ou Investimento

    private Integer ordem;

}
