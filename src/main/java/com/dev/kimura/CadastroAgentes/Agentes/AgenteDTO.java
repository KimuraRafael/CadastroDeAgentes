package com.dev.kimura.CadastroAgentes.Agentes;

import com.dev.kimura.CadastroAgentes.Enums.TipoAgentes;
import com.dev.kimura.CadastroAgentes.Tarefas.TarefasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenteDTO {

        private Long id;
        private String nome;
        private String email;
        private int idade;
        private String responsabilidade ;
        private String urlImage;
        private TipoAgentes tipoAgentes;
        private TarefasModel tarefas;


}
