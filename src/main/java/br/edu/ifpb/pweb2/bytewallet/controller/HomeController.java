package br.edu.ifpb.pweb2.bytewallet.controller;

import br.edu.ifpb.pweb2.bytewallet.model.Correntista;
import br.edu.ifpb.pweb2.bytewallet.repository.CorrentistaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("home");
        // Manda a lista de clientes para a tela inicial
        mav.addObject("correntistas", correntistaRepository.findAll());
        return mav;
    }

    // Método que cria a "sessão" do usuário
    @PostMapping("/login-cliente")
    public String loginCliente(@RequestParam("correntistaId") Long correntistaId, HttpSession session) {
        Correntista correntista = correntistaRepository.findById(correntistaId).orElse(null);
        // Salva o cliente na sessão do navegador
        session.setAttribute("correntistaLogado", correntista);
        // Redireciona para as contas DELE
        return "redirect:/contas/list";
    }
}