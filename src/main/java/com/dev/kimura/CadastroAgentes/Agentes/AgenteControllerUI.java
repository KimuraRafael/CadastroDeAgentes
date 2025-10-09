package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
        model.addAttribute("paginaAtiva", "agentes");
        return "listarAgentes";

    }

    @GetMapping("/listarAgente/{id}")
    public String listarAgentePorID(@PathVariable Long id, Model model) {

        AgenteDTO agente = agenteService.listarAgentePorId(id);

        if(agente != null){
            model.addAttribute("agente", agente);
            return "detalhesAgentes";
        }else{
            model.addAttribute("mensagem", "Agente não encontrado" );
            return "listarAgentes";
        }
    }

    @GetMapping("/novoAgente")
    public String exibirFormularioNovoAgente(Model model) {
        model.addAttribute("agente", new AgenteDTO());
        return "novoAgente"; // nome do template
    }

    @PostMapping("/salvarAgente")
    public String salvarAgente(@ModelAttribute AgenteDTO novoAgente) {
        agenteService.criarNovoAgente(novoAgente);
        return "redirect:/agentes/ui/listarAgentes";
    }


    @GetMapping("/deletarAgente/{id}")
    public String deletarAgentePorID(@PathVariable Long id) {
        agenteService.deletarAgentePorId(id);
        return "redirect:/agentes/ui/listarAgentes";
    }

    @GetMapping("/editarAgente/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        AgenteDTO agente = agenteService.listarAgentePorId(id);

        if (agente != null) {
            model.addAttribute("agente", agente);
            return "editarAgente";
        } else {
            model.addAttribute("mensagem", "Agente não encontrado");
            return "listarAgentes";
        }
    }

        @PostMapping ("/atualizarAgente/{id}")
        public String atualizarAgentePorID(@PathVariable Long id, @ModelAttribute AgenteDTO agenteAtualizado, Model model) {

            AgenteDTO atualizado = agenteService.atualizarAgente(id, agenteAtualizado);
            model.addAttribute("agente", atualizado);
            return "redirect:/agentes/ui/listarAgentes";
        }

}
