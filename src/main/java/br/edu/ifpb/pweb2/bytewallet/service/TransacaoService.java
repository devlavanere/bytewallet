package br.edu.ifpb.pweb2.bytewallet.service;

import br.edu.ifpb.pweb2.bytewallet.model.Transacao;
import br.edu.ifpb.pweb2.bytewallet.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    
    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }
    
    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }
}