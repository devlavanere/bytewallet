package br.edu.ifpb.pweb2.bytewallet.controller;

import br.edu.ifpb.pweb2.bytewallet.model.Comentario;
import br.edu.ifpb.pweb2.bytewallet.model.Transacao;
import br.edu.ifpb.pweb2.bytewallet.service.ComentarioService;
import br.edu.ifpb.pweb2.bytewallet.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private TransacaoService transacaoService;

    // UC05: Formulário para adicionar comentário (precisa saber de qual transação é)
    @GetMapping("/form/{transacaoId}")
    public ModelAndView getForm(@PathVariable("transacaoId") Long transacaoId, ModelAndView modelAndView) {
        Transacao transacao = transacaoService.buscarPorId(transacaoId);
        Comentario comentario = new Comentario();
        comentario.setTransacao(transacao); // Vincula o comentário à transação

        modelAndView.setViewName("comentarios/form");
        modelAndView.addObject("comentario", comentario);
        return modelAndView;
    }

    // UC05/UC06: Salvar e Editar (O id escondido no HTML decide se é Insert ou Update)
    @PostMapping("/save")
    public ModelAndView salvar(Comentario comentario, RedirectAttributes attr) {
        comentarioService.salvar(comentario);
        attr.addFlashAttribute("mensagem", "Comentário salvo com sucesso!");
        // Redireciona de volta para a lista de transações
        return new ModelAndView("redirect:/transacoes/list"); 
    }

    // UC06: Excluir Comentário
    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        comentarioService.excluir(id);
        attr.addFlashAttribute("mensagem", "Comentário excluído com sucesso!");
        return new ModelAndView("redirect:/transacoes/list");
    }
}