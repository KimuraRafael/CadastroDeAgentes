package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/listarAgente")
    public String listarAgentes(Model model) {
        List<AgenteDTO> listaAgentes = agenteService.listarAgentes();

        model.addAttribute("agentes", listaAgentes);
        return "listarAgentes";

    }

}
