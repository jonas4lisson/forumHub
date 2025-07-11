package com.forumhub.api.dto;

import com.forumhub.api.model.Topico;
import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String nomeAutor,
        String nomeCurso
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus().name(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
