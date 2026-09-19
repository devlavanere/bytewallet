package br.edu.ifpb.pweb2.bytewallet.controller;

import br.edu.ifpb.pweb2.bytewallet.model.Categoria;
import br.edu.ifpb.pweb2.bytewallet.model.Conta;
import br.edu.ifpb.pweb2.bytewallet.model.Transacao;
import br.edu.ifpb.pweb2.bytewallet.service.CategoriaService;
import br.edu.ifpb.pweb2.bytewallet.service.ContaService;
import br.edu.ifpb.pweb2.bytewallet.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private ContaService contaService;
    @Autowired
    private CategoriaService categoriaService;

    @ModelAttribute("contasItems")
    public List<Conta> getContas() {
        return contaService.listarTodas();
    }

    @ModelAttribute("categoriasItems")
    public List<Categoria> getCategorias() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("transacoes/form");
        modelAndView.addObject("transacao", new Transacao());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView salvar(Transacao transacao, ModelAndView modelAndView, RedirectAttributes attr) {
        transacaoService.salvar(transacao);
        attr.addFlashAttribute("mensagem", "Transação registrada com sucesso!");
        modelAndView.setViewName("redirect:/transacoes/form");
        return modelAndView;
    }
}