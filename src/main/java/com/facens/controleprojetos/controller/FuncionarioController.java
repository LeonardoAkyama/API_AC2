package com.facens.controleprojetos.controller;

import com.facens.controleprojetos.dto.response.FuncionarioResponse;
import com.facens.controleprojetos.entity.Funcionario;
import com.facens.controleprojetos.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController (FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/{setorId}")
    public FuncionarioResponse cadastrarFuncionario(@RequestBody Funcionario funcionario, @PathVariable Integer setorId) {
        return funcionarioService.cadastrarFuncionario(funcionario, setorId);
    }
}
