package br.edu.ifpb.pweb2.bytewallet.service;

import br.edu.ifpb.pweb2.bytewallet.model.Categoria;
import br.edu.ifpb.pweb2.bytewallet.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }
}