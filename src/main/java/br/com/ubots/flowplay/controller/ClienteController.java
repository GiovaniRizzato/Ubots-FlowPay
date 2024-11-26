package br.com.ubots.flowplay.controller;

import br.com.ubots.flowplay.domain.Cliente;
import br.com.ubots.flowplay.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Collection<Cliente.GetDto>> getAllProduto () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.getAll().stream().map(Cliente::getDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente.GetDto> getProdutoById (@PathVariable("id") final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.getById(id).getDto());
    }

    @PostMapping
    public ResponseEntity<Cliente.GetDto> getAllProduto (@RequestBody @Validated Cliente.CreateDto createDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.create(createDto).getDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente.GetDto> editProduto (@PathVariable("id") final Long id, @RequestBody Cliente.EditDto editDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.edit(id, editDto).getDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduto (@PathVariable("id") final Long id) {
        clienteService.remove(id);
        return ResponseEntity.ok().build();
    }
}
