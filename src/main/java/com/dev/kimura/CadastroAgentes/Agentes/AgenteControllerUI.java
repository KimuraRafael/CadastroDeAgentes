package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/agentes/ui")
public class AgenteControllerUI {

    private final AgenteService agenteService;

    AgenteControllerUI(AgenteService agenteService) {
        this.agenteService = agenteService;
    }


    // Exibir todos os Agentes (Read)
    @GetMapping("/listarAgentes")
    public String listarAgentes(Model model) {
        List<AgenteDTO> listaAgentes = agenteService.listarAgentes();

        model.addAttribute("agentes", listaAgentes);
        return "listarAgentes";

    }

    @GetMapping("/deletarAgente/{id}")
    public String deletarAgentePorID(@PathVariable Long id) {
        agenteService.deletarAgentePorId(id);
        return "redirect:/agentes/ui/listarAgentes";
    }
}
