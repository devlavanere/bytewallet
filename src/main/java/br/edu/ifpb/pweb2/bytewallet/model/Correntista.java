package br.edu.ifpb.pweb2.bytewallet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Opcional para o UC24 (Bloquear correntista)
    private boolean bloqueado = false; 
}
