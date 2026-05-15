package com.facens.controleprojetos.service;

import com.facens.controleprojetos.dto.response.FuncionarioResponse;
import com.facens.controleprojetos.dto.response.ProjetoResponse;
import com.facens.controleprojetos.dto.response.SetorResponse;
import com.facens.controleprojetos.entity.Setor;
import com.facens.controleprojetos.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService (SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public SetorResponse cadastrarSetor(Setor setor) {

        Setor setorSalvo = setorRepository.save(setor);

        return mapperSetorResponse(setorSalvo);
    }

    public List<SetorResponse> listarSetoresComFuncionarios() {

        return setorRepository
                .listarSetoresComFuncionarios()
                .stream()
                .map(this::mapperSetorResponse)
                .toList();
    }

    private SetorResponse mapperSetorResponse(Setor setor) {

        return new SetorResponse(

                setor.getNome(),

                Optional.ofNullable(setor.getFuncionarios())
                        .orElse(List.of())
                        .stream()
                        .map(funcionario -> new FuncionarioResponse(

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

                        ))
                        .toList()
        );
    }
}
