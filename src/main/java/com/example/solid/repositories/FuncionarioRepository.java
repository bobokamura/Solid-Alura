package com.example.solid.repositories;

import com.example.solid.dtos.FuncionarioDTO;
import com.example.solid.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
