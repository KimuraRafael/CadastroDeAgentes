package com.dev.kimura.CadastroAgentes.Agentes;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class AgenteController {

    private AgenteService agenteService;

    AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @GetMapping("/")
    public String bemVindo() {
        return "Bem vindo! este projeto é obra de Rafael Kimura, e provavelmente ele ainda estava treinando Spring!";
    }


    // Exibir todos os Agentes (Read)
    @GetMapping("/listarAgente")
    public ResponseEntity<List<AgenteDTO>> listarAgentes() {
      List<AgenteDTO> listaAgentes = agenteService.listarAgentes();

        return ResponseEntity.ok(listaAgentes);

    }

    // Exibir apenas um Agente (Read)
    @GetMapping("/listarAgente/{id}")
    public ResponseEntity<String> listarAgentePorID(@PathVariable Long id) {

        if(agenteService.listarAgentePorId(id) != null) {
            AgenteDTO agenteEncontrado = agenteService.listarAgentePorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Agentes encontrados:\n" + agenteEncontrado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente Não encontrado na lista");
    }

    // Adicionar um Agente (Create)
    @PostMapping("/criarAgente")
    public ResponseEntity<String> criarAgente(@RequestBody AgenteDTO agente) {

        AgenteDTO novoAgente = agenteService.criarNovoAgente(agente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Agente criado com sucesso: " + "(ID):" + novoAgente.getId() + " " + novoAgente.getNome());
    }

    // Alterar os dados dos Agentes (Update)
    @PutMapping("/atualizarAgente/{id}")
    public ResponseEntity<String> atualizarAgentePorID(@PathVariable Long id, @RequestBody AgenteDTO agenteAtualizado) {

        if (agenteService.listarAgentePorId(id) != null) {

            AgenteDTO agenteAntigo = agenteService.listarAgentePorId(id);
            AgenteDTO agenteAlterado = agenteService.atualizarAgente(id, agenteAtualizado);

            Map<String, Object> resposta = Map.of(
                    "nome", agenteAntigo.getNome() + " -> " + agenteAtualizado.getNome(),
                    "email", agenteAntigo.getEmail() + " -> " + agenteAtualizado.getEmail(),
                    "idade", agenteAntigo.getIdade() + " -> " + agenteAtualizado.getIdade(),
                    "nivelDeDemanda", agenteAntigo.getNivelDeDemanda() + " -> " + agenteAtualizado.getNivelDeDemanda()
            );
            return ResponseEntity.status(HttpStatus.OK).body("Agente Alterado com Sucesso!\n" + resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente Não encontrado para edição");

    }

    // Deletar um Agente (Delete)
    @DeleteMapping("/deletarAgente/{id}")
    public ResponseEntity<String> deletarAgentePorID(@PathVariable Long id) {

        if (agenteService.listarAgentePorId(id) != null) {
            agenteService.deletarAgentePorId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Agente "+ id + "deletada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente não existe");


    }


}
