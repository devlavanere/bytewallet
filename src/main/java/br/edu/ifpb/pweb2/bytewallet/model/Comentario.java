package br.edu.ifpb.pweb2.bytewallet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String texto;
    
    @OneToOne
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;
}
