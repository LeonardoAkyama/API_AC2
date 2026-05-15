package com.facens.controleprojetos.service;
import com.facens.controleprojetos.dto.response.FuncionarioResponse;
import com.facens.controleprojetos.dto.response.ProjetoResponse;
import com.facens.controleprojetos.entity.Funcionario;
import com.facens.controleprojetos.entity.Setor;
import com.facens.controleprojetos.repository.FuncionarioRepository;
import com.facens.controleprojetos.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioService (FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    public FuncionarioResponse cadastrarFuncionario(
            Funcionario funcionario,
            Integer setorId
    ) {

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() ->
                        new RuntimeException("Setor não encontrado"));

        funcionario.setSetor(setor);

        Funcionario funcionarioSalvo =
                funcionarioRepository.save(funcionario);

        return mapperFuncionarioResponse(funcionarioSalvo);
    }

    private FuncionarioResponse mapperFuncionarioResponse(
            Funcionario funcionario
    ) {

        return new FuncionarioResponse(

                funcionario.getNome(),

                Optional.ofNullable(funcionario.getProjetos())
                        .orElse(List.of())
                        .stream()
                        .map(projeto -> new ProjetoResponse(
                                projeto.getDescricao(),
                                projeto.getDataInicio(),
                                projeto.getDataFim()
                        ))
                        .toList()
        );
    }

}
