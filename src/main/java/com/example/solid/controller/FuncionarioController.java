package com.example.solid.controller;

import com.example.solid.dtos.FuncionarioDTO;
import com.example.solid.repositories.FuncionarioRepository;
import com.example.solid.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<?> findAllFuncionario() {
        return ResponseEntity.ok(funcionarioService.findAllFuncionario());
    }

    @PutMapping("/inserir-funcionario")
    public ResponseEntity<FuncionarioDTO> inserirFuncionario(@RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(funcionarioService.inserirFuncionario(dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> reajustarSalario(@PathVariable Long id, @RequestParam(value = "aumento") BigDecimal aumento) {
        return ResponseEntity.ok(funcionarioService.reajustarSalario(id,aumento));
    }
}
