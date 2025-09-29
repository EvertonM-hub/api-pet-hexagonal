package com.apipet.infrastructure.adapter.in.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.apipet.application.dto.LocalizacaoDTO;
import com.apipet.domain.model.LocalizacaoPet;
import com.apipet.domain.port.in.LocalizacaoUseCase;



@Controller
@RequestMapping("/api/localizacao")
public class LocalizacaoController {

    private final LocalizacaoUseCase useCase;
    
    public LocalizacaoController(LocalizacaoUseCase useCase) {
    	this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<LocalizacaoPet> registrar(@RequestBody LocalizacaoDTO dto) {
        LocalizacaoPet pet = useCase.processarLocalizacao(dto);
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    public ResponseEntity<List<LocalizacaoPet>> listarTodas() {
        return ResponseEntity.ok(useCase.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoPet> buscarPorId(@PathVariable Long id) {
        LocalizacaoPet pet = useCase.buscarPorId(id);
        return pet != null ? ResponseEntity.ok(pet) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return useCase.removerPorId(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
