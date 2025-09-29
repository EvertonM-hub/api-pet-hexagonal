package com.apipet.domain.port.out;

import com.apipet.domain.model.Endereco;

public interface PositionStackClientPort {
	
	Endereco buscarEndereco(double lat, double lon);
}
