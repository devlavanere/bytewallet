package br.edu.ifpb.pweb2.bytewallet.repository;

import br.edu.ifpb.pweb2.bytewallet.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}