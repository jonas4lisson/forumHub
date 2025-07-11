package com.forumhub.api.controller;

import com.forumhub.api.dto.DadosCadastroCurso;
import com.forumhub.api.model.Curso;
import com.forumhub.api.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroCurso dados) {
        Curso curso = new Curso();
        curso.setNome(dados.nome());
        curso.setCategoria(dados.categoria());
        cursoRepository.save(curso);
        return ResponseEntity.ok().build();
    }
}
