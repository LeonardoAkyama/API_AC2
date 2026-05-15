package com.facens.controleprojetos.dto.response;

import java.util.List;

public record FuncionarioResponse (
        String nome,
        List<ProjetoResponse> projetos
) {
}
