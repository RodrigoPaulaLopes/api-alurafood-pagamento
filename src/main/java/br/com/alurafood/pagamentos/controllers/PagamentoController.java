package br.com.alurafood.pagamentos.controllers;

import br.com.alurafood.pagamentos.dtos.PagamentoDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/pagamento")
@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public ResponseEntity<Page<PagamentoDto>> findAll(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok().body(service.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> findById(@PathVariable Long id) {
        PagamentoDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PagamentoDto> create(@RequestBody PagamentoDto dto, UriComponentsBuilder uriBuilder) {
        var pagamento = service.create(dto);
        var endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.id()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping
    public ResponseEntity<PagamentoDto> update(@RequestBody PagamentoDto dto) {
        var atualizado = service.update(dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
