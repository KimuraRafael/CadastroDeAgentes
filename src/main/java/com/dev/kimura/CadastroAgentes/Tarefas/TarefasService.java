package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteMapper;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final AgenteRepository agenteRepository;
    private final TarefasMapper tarefaMapper;
    private final AgenteMapper agenteMapper;


    public TarefasService(TarefasRepository tarefasRepository, AgenteRepository agenteRepository, TarefasMapper tarefaMapper, AgenteMapper agenteMapper) {

        this.tarefasRepository = tarefasRepository;
        this.agenteRepository = agenteRepository;
        this.tarefaMapper = tarefaMapper;
        this.agenteMapper = agenteMapper;
    }

    public List<TarefasDTO> listarTarefas() {

        List<TarefasModel> tarefas = tarefasRepository.findAll();
        return tarefas.stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }

    public TarefasDTO listarTarefaPorId(Long id){
        Optional<TarefasModel> tarefaPorId = tarefasRepository.findById(id);
        return tarefaPorId.map(tarefa -> {
            TarefasDTO dto = tarefaMapper.map(tarefa); // mapeia campos básicos

            // Lista de agentes como DTOs
            dto.setAgentes(
                    tarefa.getAgentes().stream()
                            .map(agenteMapper::map)
                            .collect(Collectors.toList())
            );

            // Lista de IDs para os checkboxes
            dto.setAgenteIds(
                    tarefa.getAgentes().stream()
                            .map(AgenteModel::getId)
                            .collect(Collectors.toList())
            );

            return dto;
        }).orElse(null);
    }
    @Transactional
    public TarefasDTO criarTarefa(TarefasDTO tarefasDTO, List<Long> agenteIds) {
        // 1️⃣ Salva a tarefa
        TarefasModel tarefa = tarefaMapper.map(tarefasDTO);
        TarefasModel tarefaSalva = tarefasRepository.save(tarefa);

        // 2️⃣ Se houver agentes selecionados
        if (agenteIds != null && !agenteIds.isEmpty()) {
            // Busca os agentes já existentes no banco
            List<AgenteModel> agentes = agenteRepository.findAllById(agenteIds);

            // Vincula a tarefa em cada agente
            for (AgenteModel agente : agentes) {
                agente.setTarefas(tarefaSalva);
            }

            // Atualiza os agentes no banco
            agenteRepository.saveAll(agentes);
        }

        // 3️⃣ Retorna DTO atualizado
        return tarefaMapper.map(tarefaSalva);
    }


    @Transactional
    public TarefasDTO atualizaTarefas(Long id, TarefasDTO tarefaAlterada) {
        Optional<TarefasModel> tarefaExistenteOpt = tarefasRepository.findById(id);

        if (tarefaExistenteOpt.isEmpty()) {
            return null;
        }

        TarefasModel tarefaExistente = tarefaExistenteOpt.get();

        // Atualiza campos simples
        if (tarefaAlterada.getDescricao() != null) {
            tarefaExistente.setDescricao(tarefaAlterada.getDescricao());
        }

        if (tarefaAlterada.getNivelDemanda() != null) {
            tarefaExistente.setNivelDemanda(tarefaAlterada.getNivelDemanda());
        }

        // Desassocia agentes antigos
        for (AgenteModel agente : tarefaExistente.getAgentes()) {
            agente.setTarefas(null);
            agenteRepository.save(agente);
        }

        // Associa os agentes selecionados
        List<AgenteModel> agentesAtualizados = new ArrayList<>();
        if (tarefaAlterada.getAgenteIds() != null) {
            for (Long agenteId : tarefaAlterada.getAgenteIds()) {
                AgenteModel agente = agenteRepository.findById(agenteId).orElse(null);
                if (agente != null) {
                    agente.setTarefas(tarefaExistente); // atualiza a relação bidirecional
                    agenteRepository.save(agente);
                    agentesAtualizados.add(agente);
                }
            }
        }

        tarefaExistente.setAgentes(agentesAtualizados);
        TarefasModel tarefaSalva = tarefasRepository.save(tarefaExistente);

        return tarefaMapper.map(tarefaSalva);
    }


    public void deletarTarefaPorId(Long id) {

        TarefasModel tarefa = tarefasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        if (!tarefa.getAgentes().isEmpty()) {
            throw new IllegalStateException("Não é possível deletar a tarefa, pois existem agentes associados.");
        }
        tarefasRepository.deleteById(id);
    }
}
