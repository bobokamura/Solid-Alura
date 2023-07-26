package com.example.solid.services;

import com.example.solid.entities.Funcionario;
import com.example.solid.services.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ValidacaoPeriodicidadeEntreReajustes implements ValidacaoReajuste {

    @Override
    public void validar(Funcionario funcionario, BigDecimal aumento) {
        LocalDate dataUltimoReajuste = funcionario.getDataUltimoReajuste();
        LocalDate dataAtual = LocalDate.now();
        long mesesEntreReajustes = ChronoUnit.MONTHS.between(dataUltimoReajuste, dataAtual);
        if (mesesEntreReajustes < 6) {
            throw new ValidacaoException("Reajuste somente após 6 meses!");
        }
    }
}
