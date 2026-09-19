package br.edu.ifpb.pweb2.bytewallet.service;

import br.edu.ifpb.pweb2.bytewallet.model.Correntista;
import br.edu.ifpb.pweb2.bytewallet.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public Correntista salvar(Correntista correntista) {
        return correntistaRepository.save(correntista);
    }

    public List<Correntista> listarTodos() {
        return correntistaRepository.findAll();
    }
}