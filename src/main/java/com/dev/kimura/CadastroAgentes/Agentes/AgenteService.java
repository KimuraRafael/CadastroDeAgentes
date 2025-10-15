package com.dev.kimura.CadastroAgentes.Agentes;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgenteService {

    private final AgenteRepository agenteRepository;
    private final AgenteMapper agenteMapper;

    public AgenteService(AgenteRepository agenteRepository, AgenteMapper agenteMapper){
         this.agenteRepository = agenteRepository;
         this.agenteMapper = agenteMapper;
     }

     // Função para listar meus Agentes
    public List<AgenteDTO> listarAgentes(){
        List<AgenteModel> agentes = agenteRepository.findAll();

        return agentes.stream() // Stream é uma API que percorre por toda as informações que serão listadas pelo JPA agenteRepository.findAll()
                .map(agenteMapper::map) // Map converte os dados encontrados para eles serem do Tipo AgenteDTO
                .collect(Collectors.toList());// Collectors é o que realmente retorna a lista (poderia colocar o toList direto mas vou manter de exemplo)
    }

    // Função para listar um agente específico
    public AgenteDTO listarAgentePorId(Long id){

        Optional<AgenteModel> agentePorId = agenteRepository.findById(id);

        return agentePorId.map(agenteMapper::map)
                .orElse(null);
    }

    // Função para criar um novo agente
    public AgenteDTO criarNovoAgente(AgenteDTO agenteDTO){
        AgenteModel agente = agenteMapper.map(agenteDTO);
        agenteRepository.save(agente);
        return agenteMapper.map(agente);
    }

    // Delete tem que ser um metodo void pois não há necessidade retornar nada para o servidor
    public void deletarAgentePorId(Long id){
        agenteRepository.deleteById(id); //Não preciso passar a responsabilidade para o DTO porque ela não depende do Model, ela apenas Recebe o ID e eu realizo uma Query de Delete.
    }

    // Função de Atualização de dados, a ideia é sobreescrever dados existentes recebendo as alterações entregues pelos usuários.
    public AgenteDTO atualizarAgente(Long id, AgenteDTO agenteAlterado){

        return agenteRepository.findById(id).map(agenteExistente -> {
            if (agenteAlterado.getNome() != null) {
                agenteExistente.setNome(agenteAlterado.getNome());
            }
            if (agenteAlterado.getEmail() != null) {
                agenteExistente.setEmail(agenteAlterado.getEmail());
            }
            if (agenteAlterado.getIdade() > 0) {
                agenteExistente.setIdade(agenteAlterado.getIdade());
            }
            if (agenteAlterado.getResponsabilidade() != null) {
                agenteExistente.setResponsabilidade(agenteAlterado.getResponsabilidade());
            }
            if (agenteAlterado.getUrlImage() != null) {
                agenteExistente.setUrlImage(agenteAlterado.getUrlImage());
            }
            if (agenteAlterado.getTipoAgentes() != null) {
                agenteExistente.setTipoAgentes(agenteAlterado.getTipoAgentes());
            }
            AgenteModel salvo = agenteRepository.save(agenteExistente);
            return agenteMapper.map(salvo);
        }).orElse(null);
    }
}
