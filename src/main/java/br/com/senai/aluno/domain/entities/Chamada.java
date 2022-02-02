package br.com.senai.aluno.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chamada")
public class Chamada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long matricula_aluno;

    private boolean presenca;

    private LocalDateTime data_hora;
}
