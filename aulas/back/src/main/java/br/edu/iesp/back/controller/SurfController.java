package br.edu.iesp.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surf")

public class SurfController {
    @GetMapping
    public String onda(){
        return "Onda Spring";
    }
}
