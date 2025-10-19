package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgenteRepository extends JpaRepository<AgenteModel, Long> {

    List<AgenteModel> findByTarefasIsNull();
}
