package com.example.solid.services;

import com.example.solid.dtos.FuncionarioDTO;
import com.example.solid.entities.Funcionario;
import com.example.solid.repositories.FuncionarioRepository;
import com.example.solid.services.exceptions.ValidacaoException;
import com.example.solid.services.promocao.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final List<ValidacaoReajuste> validacoes;
    private final PromocaoService promocaoService;

    public FuncionarioService(List<ValidacaoReajuste> validacoes, PromocaoService promocaoService) {
        this.validacoes = validacoes;
        this.promocaoService = promocaoService;
    }


    @Transactional(readOnly = true)
    public Object findAllFuncionario() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioDTO inserirFuncionario(FuncionarioDTO dto) {
        Funcionario entity = new Funcionario();
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setCargo(dto.getCargo());
        entity.setSalario(dto.getSalario());
        entity.setDataUltimoReajuste(LocalDate.now());
        funcionarioRepository.save(entity);
        return new FuncionarioDTO(entity);
    }

    public Object reajustarSalario(Long id, BigDecimal value) {
        try {
            return reajustarSalarioDoFuncionario(id, value);
        } catch (EntityNotFoundException | HttpServerErrorException.InternalServerError e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    public FuncionarioDTO reajustarSalarioDoFuncionario(Long id, BigDecimal aumento) {
        Optional<Funcionario> opt = funcionarioRepository.findById(id);
        Funcionario funcionario = opt.orElseThrow(() -> new EntityNotFoundException("Id: " + id + " não encontrado"));

        this.promocaoService.promover(funcionario, true);
        this.validacoes.forEach(v -> v.validar(funcionario, aumento));

        BigDecimal salarioReajustado = funcionario.getSalario().add(aumento);
        funcionario.atualizarSalario(salarioReajustado);
        funcionarioRepository.save(funcionario);
        return new FuncionarioDTO(funcionario);
    }
}
