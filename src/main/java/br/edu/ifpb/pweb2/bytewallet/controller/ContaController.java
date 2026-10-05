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

    @GetMapping("/form")
    public ModelAndView getForm(HttpSession session) {
        // Se alguém tentar acessar a URL direto sem estar logado, expulsa para a Home
        if (session.getAttribute("correntistaLogado") == null) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView mav = new ModelAndView("contas/form");
        mav.addObject("conta", new Conta());
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView salvar(Conta conta, HttpSession session, RedirectAttributes attr) {
        // Pega quem é o usuário logado na sessão atual
        Correntista logado = (Correntista) session.getAttribute("correntistaLogado");
        
        if (logado != null) {
            // TRAVA DE SEGURANÇA: Obriga a conta a pertencer a quem está logado
            conta.setCorrentista(logado);
        }

        contaService.salvar(conta);
        attr.addFlashAttribute("mensagem", "Conta cadastrada com sucesso!");
        return new ModelAndView("redirect:/contas/list");
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

        mav.setViewName("contas/list");
        mav.addObject("contas", contaRepository.findByCorrentistaId(logado.getId()));
        
        return mav;
    }

}
