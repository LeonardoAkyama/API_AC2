package com.facens.controleprojetos.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ProjetoComFuncionariosResponse(
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<FuncionarioResumoResponse> funcionarios
) {
}
