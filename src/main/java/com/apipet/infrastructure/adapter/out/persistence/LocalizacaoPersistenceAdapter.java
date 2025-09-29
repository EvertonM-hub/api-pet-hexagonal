package com.apipet.infrastructure.adapter.out.persistence;

import com.apipet.domain.model.LocalizacaoPet;




import com.apipet.domain.port.out.LocalizacaoPersistencePort;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalizacaoPersistenceAdapter implements LocalizacaoPersistencePort {

    private final LocalizacaoRepository repository;
    
    public LocalizacaoPersistenceAdapter (LocalizacaoRepository repository) {
    	this.repository = repository;
    }

    public LocalizacaoPet salvar(LocalizacaoPet pet) {
        return repository.save(pet);
    }

    public List<LocalizacaoPet> listarTodas() {
        return repository.findAll();
    }

    public LocalizacaoPet buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean removerPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


