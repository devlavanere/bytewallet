package br.edu.ifpb.pweb2.bytewallet.repository;

import br.edu.ifpb.pweb2.bytewallet.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByCorrentistaId(Long id);
}