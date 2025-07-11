package com.forumhub.api.controller;

import com.forumhub.api.dto.DadosAtualizacaoTopico;
import com.forumhub.api.dto.DadosDetalhamentoTopico;
import com.forumhub.api.dto.DadosListagemTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.forumhub.api.dto.DadosCadastroTopico;
import com.forumhub.api.model.Curso;
import com.forumhub.api.model.Topico;
import com.forumhub.api.model.Usuario;
import com.forumhub.api.repository.CursoRepository;
import com.forumhub.api.repository.TopicoRepository;
import com.forumhub.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(
            TopicoRepository topicoRepository,
            UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository
    ) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {

        // Verifica duplicidade
        if (topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem()).isPresent()) {
            return ResponseEntity.badRequest().body("Tópico já existe com o mesmo título e mensagem.");
        }

        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);

        topicoRepository.save(topico);

        return ResponseEntity.ok("Tópico cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemTopico>> listar() {
        var topicos = topicoRepository.findAll()
                .stream()
                .map(DadosListagemTopico::new)
                .toList();

        return ResponseEntity.ok(topicos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado"));

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        // Reaproveitando lógica de verificação de duplicidade
        if (topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem()).isPresent()) {
            return ResponseEntity.badRequest().body("Já existe um tópico com o mesmo título e mensagem.");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        return ResponseEntity.ok("Tópico atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
