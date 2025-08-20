package com.dev.kimura.CadastroAgentes.Tarefas;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {

    private TarefasRepository tarefasRepository;

    public TarefasService(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    public List<TarefasModel> listarTarefas(){

        return tarefasRepository.findAll();
    }

    public TarefasModel listarTarefaPorId(Long id){
        Optional<TarefasModel> tarefaPorId = tarefasRepository.findById(id);
        return tarefaPorId.orElse(null);
    }
}
