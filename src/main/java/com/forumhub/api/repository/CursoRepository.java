package com.forumhub.api.repository;

// CursoRepository.java

import com.forumhub.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
