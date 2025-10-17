package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefaMapper;

    public TarefasService(TarefasRepository tarefasRepository, TarefasMapper tarefaMapper) {

        this.tarefasRepository = tarefasRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public List<TarefasDTO> listarTarefas(){

        List<TarefasModel> tarefas = tarefasRepository.findAll();
        return tarefas.stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }

    public TarefasDTO listarTarefaPorId(Long id){
        Optional<TarefasModel> tarefaPorId = tarefasRepository.findById(id);
        return tarefaPorId.map(tarefaMapper::map)
                .orElse(null);
    }

    public TarefasDTO criarTarefa(TarefasDTO tarefasDTO){
        TarefasModel tarefas = tarefaMapper.map(tarefasDTO);
        tarefasRepository.save(tarefas);
        return tarefaMapper.map(tarefas);
    }

    public TarefasDTO atualizaTarefas(Long id, TarefasDTO tarefaAlterada) {
        Optional<TarefasModel> tarefaExistenteOpt = tarefasRepository.findById(id);

        if (tarefaExistenteOpt.isEmpty()) {
            return null;
        }

        TarefasModel tarefaExistente = tarefaExistenteOpt.get();

        // Atualiza apenas os campos que vierem preenchidos
        if (tarefaAlterada.getDescricao() != null) {
            tarefaExistente.setDescricao(tarefaAlterada.getDescricao());
        }

        if (tarefaAlterada.getNivelDemanda() != null) {
            tarefaExistente.setNivelDemanda(tarefaAlterada.getNivelDemanda());
        }


        TarefasModel tarefaSalva = tarefasRepository.save(tarefaExistente);
        return tarefaMapper.map(tarefaSalva);
    }

    public void deletarTarefaPorId(Long id){
        tarefasRepository.deleteById(id);
    }
}
