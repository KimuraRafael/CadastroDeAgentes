package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteRepository;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tarefas/ui")
public class TarefasControllerUI {

    private final TarefasService tarefasService;
    private final AgenteService agenteService;
    private final AgenteRepository agenteRepository;


    public TarefasControllerUI(TarefasService tarefasService, AgenteService agenteService, AgenteRepository agenteRepository) {
        this.tarefasService = tarefasService;
        this.agenteService = agenteService;
        this.agenteRepository = agenteRepository;
    }

    @GetMapping("/listarTarefas")
    public String listarTarefas(Model model) {

        List<TarefasDTO> listaTarefas = tarefasService.listarTarefas();

        model.addAttribute("tarefas", listaTarefas);
        model.addAttribute("paginaAtiva", "tarefas");
        return "listarTarefas";

    }

    @GetMapping("/listarTarefa/{id}")
    public String listarTarefasPorId(@PathVariable Long id, Model model) {

        TarefasDTO tarefa = tarefasService.listarTarefaPorId(id);

        if (tarefa != null) {
            model.addAttribute("tarefa", tarefa);
            return "detalhesTarefa";
        } else {
            model.addAttribute("mensagem", "Tarefa não encontrada");
            return "listarTarefas";
        }
    }


    @GetMapping("/novaTarefa")
    public String exibirFormularioNovaTarefa(Model model) {
        model.addAttribute("tarefa", new TarefasDTO());
        model.addAttribute("agentesDisponiveis", agenteRepository.findByTarefasIsNull());
        return "novaTarefa";
    }


    @PostMapping("/salvarTarefa")
    public String salvarTarefa(
            @ModelAttribute("tarefa") TarefasDTO tarefaDTO,
            @RequestParam(value = "agenteIds", required = false) List<Long> agenteIds) {

        tarefasService.criarTarefa(tarefaDTO, agenteIds);
        return "redirect:/tarefas/ui/listarTarefas"; // redireciona pra listagem
    }

    @GetMapping("/editarTarefa/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        TarefasDTO tarefa = tarefasService.listarTarefaPorId(id);
        List<AgenteDTO> todosAgentes = agenteService.listarAgentes();

        model.addAttribute("tarefa", tarefa);
        model.addAttribute("todosAgentes", todosAgentes); // importante!
        return "editarTarefa";
    }


    @PostMapping("/atualizarTarefa/{id}")
    public String atualizarTarefa(@PathVariable Long id, @ModelAttribute TarefasDTO tarefaAtualizada) {
        tarefasService.atualizaTarefas(id, tarefaAtualizada);
        return "redirect:/tarefas/ui/listarTarefas";
    }

    @GetMapping("/deletarTarefa/{id}")
    public String deletarTarefaPorId(@PathVariable Long id,  RedirectAttributes redirectAttrs) {


        try {
            tarefasService.deletarTarefaPorId(id);
            redirectAttrs.addFlashAttribute("mensagemSucesso", "Tarefa deletada com sucesso!");
        } catch (IllegalStateException e) {
            redirectAttrs.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/tarefas/ui/listarTarefas";

    }

}
