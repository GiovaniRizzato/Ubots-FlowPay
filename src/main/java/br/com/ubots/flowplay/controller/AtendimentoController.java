package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<Collection<Atendimento.AtendimentoGetDto>> getAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAll().stream().map(Atendimento::getDto).collect(Collectors.toSet()));
    }

    @GetMapping("/assunto/{assunto}")
    public ResponseEntity<Collection<Atendimento.AtendimentoGetDto>> getAllByAssunto (@PathVariable("assunto") final Atendimento.Assunto assunto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAllByAssunto(assunto).stream().map(Atendimento::getDto).collect(Collectors.toSet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento.AtendimentoGetDto> getById (@PathVariable("id") final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getById(id).getDto());
    }

    @PostMapping
    public ResponseEntity<Atendimento.AtendimentoGetDto> create (@RequestBody @Validated Atendimento.AtendimentoCreateDto createDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.create(createDto).getDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento.AtendimentoGetDto> edit (@PathVariable("id") final Long id, @RequestBody Atendimento.AtendimentoEditDto editDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.edit(id, editDto).getDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable("id") final Long id) {
        atendimentoService.remove(id);
        return ResponseEntity.ok().build();
    }
}
