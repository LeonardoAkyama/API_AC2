package com.facens.controleprojetos.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Projeto> projetos = new ArrayList<>();
}
