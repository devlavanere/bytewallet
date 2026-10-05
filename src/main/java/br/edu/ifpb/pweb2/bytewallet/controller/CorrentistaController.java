package br.edu.ifpb.pweb2.bytewallet.controller;

import br.edu.ifpb.pweb2.bytewallet.model.Correntista;
import br.edu.ifpb.pweb2.bytewallet.service.CorrentistaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    // Rota GET para abrir o formulário
    @GetMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("correntistas/form");
        modelAndView.addObject("correntista", new Correntista());
        return modelAndView;
    }

   @PostMapping("/save")
    public ModelAndView salvar(@Valid Correntista correntista, BindingResult result, RedirectAttributes attr) {
        
        // Se o Spring encontrar algum erro nas anotações da Entidade...
        if (result.hasErrors()) {
            // Ele aborta o salvamento e devolve a tela de formulário com os erros!
            ModelAndView mav = new ModelAndView("correntistas/form");
            mav.addObject("correntista", correntista); // Mantém os dados que o usuário já digitou
            return mav;
        }

        correntistaService.salvar(correntista);;
        attr.addFlashAttribute("mensagem", "Correntista cadastrado com sucesso!");
        return new ModelAndView("redirect:/correntistas/list");
    }

    // Rota GET para listar todos os correntistas
    @GetMapping("/list")
    public ModelAndView listar(ModelAndView modelAndView) {
        modelAndView.setViewName("correntistas/list");
        modelAndView.addObject("correntistas", correntistaService.listarTodos());
        return modelAndView;
    }
}