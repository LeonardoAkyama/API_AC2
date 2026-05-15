package com.facens.controleprojetos.service;

import com.facens.controleprojetos.dto.response.ProjetoResponse;
import com.facens.controleprojetos.dto.response.FuncionarioResumoResponse;
import com.facens.controleprojetos.dto.response.ProjetoComFuncionariosResponse;
import com.facens.controleprojetos.entity.Funcionario;
import com.facens.controleprojetos.entity.Projeto;
import com.facens.controleprojetos.repository.FuncionarioRepository;
import com.facens.controleprojetos.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService (ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public ProjetoResponse cadastrarProjeto(Projeto projeto) {

        if (projeto.getDataFim().isBefore(projeto.getDataInicio())) {
            throw new RuntimeException("Data inválida");
        }

        Projeto projetoSalvo = projetoRepository.save(projeto);

        return mapperProjetoResponse(projetoSalvo);
    }

    public ProjetoResponse buscarProjetoPorId(Integer id) {

        Projeto projeto = projetoRepository
                .buscarProjetoComFuncionarios(id)
                .orElseThrow(() ->
                        new RuntimeException("Projeto não encontrado"));

        return mapperProjetoResponse(projeto);
    }

    public ProjetoComFuncionariosResponse buscarProjetoComFuncionarios(
            Integer id
    ) {

        Projeto projeto = projetoRepository
                .buscarProjetoComFuncionarios(id)
                .orElseThrow(() ->
                        new RuntimeException("Projeto nÃ£o encontrado"));

        return mapperProjetoComFuncionariosResponse(projeto);
    }

    public List<ProjetoResponse> buscarProjetosPorPeriodo(
            LocalDate inicio,
            LocalDate fim
    ) {

        return projetoRepository
                .findByDataInicioBetween(inicio, fim)
                .stream()
                .map(this::mapperProjetoResponse)
                .toList();
    }

    public List<ProjetoResponse> buscarProjetosPorFuncionario(
            Integer funcionarioId
    ) {

        return projetoRepository
                .buscarProjetosPorFuncionario(funcionarioId)
                .stream()
                .map(this::mapperProjetoResponse)
                .toList();
    }

    public ProjetoResponse vincularFuncionario(
            Integer projetoId,
            Integer funcionarioId
    ) {

        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() ->
                        new RuntimeException("Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository
                .findById(funcionarioId)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        projeto.getFuncionarios().add(funcionario);

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return mapperProjetoResponse(projetoAtualizado);
    }

    private ProjetoResponse mapperProjetoResponse(Projeto projeto) {

        return new ProjetoResponse(
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataFim()
        );
    }

    private ProjetoComFuncionariosResponse mapperProjetoComFuncionariosResponse(
            Projeto projeto
    ) {

        return new ProjetoComFuncionariosResponse(
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataFim(),
                projeto.getFuncionarios()
                        .stream()
                        .map(funcionario -> new FuncionarioResumoResponse(
                                funcionario.getNome()
                        ))
                        .toList()
        );
    }
}
