package com.facens.controleprojetos.controller;

import com.facens.controleprojetos.dto.response.SetorResponse;
import com.facens.controleprojetos.entity.Setor;
import com.facens.controleprojetos.service.SetorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public SetorResponse cadastrarSetor(@RequestBody Setor setor) {
        return setorService.cadastrarSetor(setor);
    }

    @GetMapping
    public List<SetorResponse> listarSetoresComFuncionarios() {
        return setorService.listarSetoresComFuncionarios();
    }
}
