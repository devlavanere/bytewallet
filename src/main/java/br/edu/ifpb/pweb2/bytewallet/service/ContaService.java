package br.edu.ifpb.pweb2.bytewallet.service;

import br.edu.ifpb.pweb2.bytewallet.model.Conta;
import br.edu.ifpb.pweb2.bytewallet.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listarTodos() {
        return contaRepository.findAll();
    }

}
