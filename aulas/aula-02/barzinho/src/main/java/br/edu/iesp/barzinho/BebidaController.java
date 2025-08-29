package br.edu.iesp.barzinho;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bebidas")

public class BebidaController {
    @GetMapping("/recuperar")
    public Bebida obterBebida(){
        Bebida bebida = new Bebida();
        bebida.setNome("asas000");
        bebida.setAnoFabricacao(2025);
        return bebida;
    }
}
