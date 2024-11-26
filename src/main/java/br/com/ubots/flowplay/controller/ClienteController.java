package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Cliente;
import br.com.ubots.flowplay.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Collection<Cliente.ClienteGetDto>> getAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.getAll().stream().map(Cliente::getDto).collect(Collectors.toSet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente.ClienteGetDto> getById (@PathVariable("id") final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.getById(id).getDto());
    }

    @PostMapping
    public ResponseEntity<Cliente.ClienteGetDto> create (@RequestBody @Validated Cliente.ClienteCreateDto createDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.create(createDto).getDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente.ClienteGetDto> edit (@PathVariable("id") final Long id, @RequestBody Cliente.ClienteEditDto editDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.edit(id, editDto).getDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable("id") final Long id) {
        clienteService.remove(id);
        return ResponseEntity.ok().build();
    }
}
