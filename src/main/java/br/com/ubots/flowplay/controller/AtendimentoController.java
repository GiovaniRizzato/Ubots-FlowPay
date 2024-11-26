package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<Collection<Atendimento.AtendimentoGetShortDto>> getAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAll().stream().map(Atendimento::getShortDto).collect(Collectors.toSet()));
    }

    @GetMapping("/setor/{setor}")
    public ResponseEntity<Collection<Atendimento.AtendimentoGetShortDto>> getAllBySetor (@PathVariable("setor") final Atendimento.Setor setor) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAllBySetor(setor).stream().map(Atendimento::getShortDto).collect(Collectors.toSet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento.AtendimentoGetFullDto> getById (@PathVariable("id") final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getById(id).getFullDto());
    }

    @PostMapping
    public ResponseEntity<Atendimento.AtendimentoGetFullDto> create (@RequestBody @Validated Atendimento.AtendimentoCreateDto createDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.create(createDto).getFullDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento.AtendimentoGetFullDto> edit (@PathVariable("id") final Long id, @RequestBody Atendimento.AtendimentoEditDto editDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.edit(id, editDto).getFullDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable("id") final Long id) {
        atendimentoService.remove(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionException(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
