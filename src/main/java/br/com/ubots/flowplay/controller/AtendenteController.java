package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Atendente;
import br.com.ubots.flowplay.domain.Atendimento;
import br.com.ubots.flowplay.service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteService atendimentoService;

    @GetMapping
    public ResponseEntity<Collection<Atendente.AtendenteGetShortDto>> getAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAll().stream().map(Atendente::getShortDto).collect(Collectors.toSet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente.AtendenteGetFullDto> getById (@PathVariable("id") final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getById(id).getFullDto());
    }

    @GetMapping("/setor/{setor}/avalible")
    public ResponseEntity<Collection<Atendente.AtendenteGetShortDto>> getAvalibleBySetor (@PathVariable("setor") final Atendimento.Setor setor) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.getAllAvalible(setor).stream().map(Atendente::getShortDto).collect(Collectors.toSet()));
    }

    @PostMapping
    public ResponseEntity<Atendente.AtendenteGetFullDto> create (@RequestBody @Validated Atendente.AtendenteCreateDto createDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(atendimentoService.create(createDto).getFullDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente.AtendenteGetFullDto> edit (@PathVariable("id") final Long id, @RequestBody Atendente.AtendenteEditDto editDto) {
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
