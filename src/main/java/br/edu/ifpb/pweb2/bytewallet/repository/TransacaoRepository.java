package br.edu.ifpb.pweb2.bytewallet.repository;

import br.edu.ifpb.pweb2.bytewallet.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}