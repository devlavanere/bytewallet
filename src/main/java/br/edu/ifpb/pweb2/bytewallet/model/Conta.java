package br.edu.ifpb.pweb2.bytewallet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String descricao;

    private String tipo; // Corrente ou Cartao

    @Column(name = "dia_fechamento")
    private Integer diaFechamento;

    @ManyToOne
    @JoinColumn(name = "correntista_id")
    private Correntista correntista;

}
