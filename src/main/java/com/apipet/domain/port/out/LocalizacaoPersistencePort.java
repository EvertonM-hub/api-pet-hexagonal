package com.apipet.domain.port.out;

import com.apipet.domain.model.LocalizacaoPet;

import java.util.List;

public interface LocalizacaoPersistencePort {
	
	 LocalizacaoPet salvar(LocalizacaoPet pet);
	    List<LocalizacaoPet> listarTodas();
	    LocalizacaoPet buscarPorId(Long id);
	    boolean removerPorId(Long id);
}
