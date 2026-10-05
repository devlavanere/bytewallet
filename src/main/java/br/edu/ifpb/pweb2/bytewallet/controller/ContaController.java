package br.edu.ifpb.pweb2.bytewallet.controller;

import br.edu.ifpb.pweb2.bytewallet.model.Conta;
import br.edu.ifpb.pweb2.bytewallet.model.Correntista;
import br.edu.ifpb.pweb2.bytewallet.repository.ContaRepository;
import br.edu.ifpb.pweb2.bytewallet.service.ContaService;
import br.edu.ifpb.pweb2.bytewallet.service.CorrentistaService;
import jakarta.servlet.http.HttpSession;

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
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private CorrentistaService correntistaService; // Injetado para buscar a lista do <select>

    @Autowired
    private ContaRepository contaRepository;

    // Esse método disponibiliza a lista de correntistas para os templates HTML desse controller
    @ModelAttribute("correntistaItems")
    public List<Correntista> getCorrentistas() {
        return correntistaService.listarTodos();
    }

    // Método para abrir a tela
    @GetMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta", new Conta());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView salvar(Conta conta, ModelAndView modelAndView, RedirectAttributes attr) {
        contaService.salvar(conta);
        attr.addFlashAttribute("mensagem", "Conta cadastrada com sucesso!");
        modelAndView.setViewName("redirect:/contas/list"); 
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listar(HttpSession session) {
        // Tenta pegar o usuário da sessão
        Correntista logado = (Correntista) session.getAttribute("correntistaLogado");
        
        ModelAndView mav = new ModelAndView();
        
        // Se ninguém "logou", manda de volta para a tela inicial
        if (logado == null) {
            mav.setViewName("redirect:/");
            return mav;
        }

        // Se logou, busca SÓ as contas dele usando a nossa nova "mágica" do repositório
        mav.setViewName("contas/list");
        mav.addObject("contas", contaRepository.findByCorrentistaId(logado.getId()));
        
        return mav;
    }

}
