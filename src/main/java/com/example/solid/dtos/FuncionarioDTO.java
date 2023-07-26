package com.example.solid.dtos;

import com.example.solid.entities.Cargo;
import com.example.solid.entities.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Cargo cargo;
    private BigDecimal salario;
    private LocalDate dataUltimoReajuste;

    public FuncionarioDTO(Funcionario entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        cargo = entity.getCargo();
        salario = entity.getSalario();
        dataUltimoReajuste = entity.getDataUltimoReajuste();
    }
}
