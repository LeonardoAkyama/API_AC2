package com.facens.controleprojetos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "setor")
    private List<Funcionario> funcionarios = new ArrayList<>();
}
