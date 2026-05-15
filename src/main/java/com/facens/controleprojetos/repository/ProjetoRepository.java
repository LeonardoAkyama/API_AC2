package com.facens.controleprojetos.repository;

import com.facens.controleprojetos.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    @Query("""
            SELECT DISTINCT p
            FROM Projeto p
            LEFT JOIN FETCH p.funcionarios
            WHERE p.id = :id
            """)
    Optional<Projeto> buscarProjetoComFuncionarios(Integer id);

    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);

    @Query("""
            SELECT DISTINCT p
            FROM Projeto p
            JOIN p.funcionarios f
            WHERE f.id = :funcionarioId
            """)
    List<Projeto> buscarProjetosPorFuncionario(Integer funcionarioId);
}
