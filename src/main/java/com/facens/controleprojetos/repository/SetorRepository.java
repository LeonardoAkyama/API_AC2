package com.facens.controleprojetos.repository;

import com.facens.controleprojetos.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query("""
            SELECT DISTINCT s
            FROM Setor s
            LEFT JOIN FETCH s.funcionarios
            """)
    List<Setor> listarSetoresComFuncionarios();
}
