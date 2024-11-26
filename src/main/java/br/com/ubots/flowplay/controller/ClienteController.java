package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Cliente;
import br.com.ubots.flowplay.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
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
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clienteService.getById(id).getDto());
        } catch (NoSuchElementException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente.ClienteGetDto> create (@RequestBody @Validated Cliente.ClienteCreateDto createDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clienteService.create(createDto).getDto());
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.ALREADY_REPORTED)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente.ClienteGetDto> edit (@PathVariable("id") final Long id, @RequestBody Cliente.ClienteEditDto editDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clienteService.edit(id, editDto).getDto());
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.ALREADY_REPORTED)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable("id") final Long id) {
        try {
            clienteService.remove(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
