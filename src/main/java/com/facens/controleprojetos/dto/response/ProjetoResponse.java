package com.facens.controleprojetos.dto.response;

import java.time.LocalDate;

public record ProjetoResponse(
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
