package com.facens.controleprojetos.controller;

import com.facens.controleprojetos.dto.response.ProjetoComFuncionariosResponse;
import com.facens.controleprojetos.dto.response.ProjetoResponse;
import com.facens.controleprojetos.entity.Projeto;
import com.facens.controleprojetos.service.ProjetoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController (ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ProjetoResponse cadastrarProjeto(@RequestBody Projeto projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }

    @GetMapping("/{id}")
    public ProjetoResponse buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @GetMapping("/{id}/funcionarios")
    public ProjetoComFuncionariosResponse buscarProjetoComFuncionarios(
            @PathVariable Integer id
    ) {
        return projetoService.buscarProjetoComFuncionarios(id);
    }

    @GetMapping("/periodo")
    public List<ProjetoResponse> buscarProjetosPorPeriodo(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        return projetoService.buscarProjetosPorPeriodo(inicio, fim);
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public List<ProjetoResponse> buscarProjetosPorFuncionario(@PathVariable Integer funcionarioId) {
        return projetoService.buscarProjetosPorFuncionario(funcionarioId);
    }

    @PutMapping("/{projetoId}/funcionario/{funcionarioId}")
    public ProjetoResponse vincularFuncionario(@PathVariable Integer projetoId, @PathVariable Integer funcionarioId) {
        return projetoService.vincularFuncionario(
                projetoId,
                funcionarioId
        );
    }
}
