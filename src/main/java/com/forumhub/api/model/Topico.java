package com.forumhub.api.model;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;


    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

public Topico(String titulo, String mensagem, Usuario autor, Curso curso) {
    this.titulo = titulo;
    this.mensagem = mensagem;
    this.autor = autor;
    this.curso = curso;
    this.dataCriacao = java.time.LocalDateTime.now();
    this.status = StatusTopico.NAO_RESPONDIDO;
}
}

