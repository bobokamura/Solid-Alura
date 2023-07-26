package com.example.solid.services.promocao;

import com.example.solid.entities.Cargo;
import com.example.solid.entities.Funcionario;
import com.example.solid.services.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class PromocaoService {

    public void promover(Funcionario funcionario, boolean metaBatida) {
        Cargo cargoAtual = funcionario.getCargo();
        if (Cargo.GERENTE == cargoAtual) {
            throw new ValidacaoException("Gerentes não podem ser promovidos.");
        }
        if (!metaBatida) {
            throw new ValidacaoException("Meta não batida, " + funcionario.getCargo() + " não será promovido.");
        }
        Cargo novoCargo = cargoAtual.getProximoCargo();
        funcionario.promover(novoCargo);
    }
}
