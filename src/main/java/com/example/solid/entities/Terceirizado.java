package com.example.solid.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Terceirizado {

    //COMPOSIÇÃO no lugar de HERANÇA
    private DadosPessoais dadosPessoais;
    private String empresa;
}
