package br.edu.ifpb.pweb2.bytewallet.service;

import br.edu.ifpb.pweb2.bytewallet.model.Comentario;
import br.edu.ifpb.pweb2.bytewallet.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // UC05 e UC06: Salvar/Editar comentário
    public Comentario salvar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Usado caso precise buscar um comentário específico para edição
    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    // UC06: Excluir comentário
    public void excluir(Long id) {
        comentarioRepository.deleteById(id);
    }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }
}