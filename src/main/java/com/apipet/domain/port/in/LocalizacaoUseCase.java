package com.apipet.domain.port.in;


import com.apipet.domain.model.LocalizacaoPet;

import com.apipet.application.dto.LocalizacaoDTO;


import java.util.List;


public interface LocalizacaoUseCase {
		
	  LocalizacaoPet processarLocalizacao(LocalizacaoDTO dto);
	    List<LocalizacaoPet> listarTodas();
	    LocalizacaoPet buscarPorId(Long id);
	    boolean removerPorId(Long id);
		
}
