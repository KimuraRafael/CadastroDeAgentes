package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AgenteController {

    @GetMapping("/welcome")
    public String bemVindo(){
        return "Bem vindo! este projeto é obra de Rafael Kimura, e provavelmente ele ainda estava treinando Spring!";
    }
}
