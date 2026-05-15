package com.facens.controleprojetos.dto.response;

import java.util.List;

public record SetorResponse(
        String nome,
        List<FuncionarioResponse> funcionarios
) {
}
