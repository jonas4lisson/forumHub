package com.forumhub.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
        @NotBlank String nome,
        @NotBlank String categoria
) {}
