package com.apipet.application.service;

import com.apipet.application.dto.LocalizacaoDTO;
import com.apipet.domain.model.Endereco;
import com.apipet.domain.model.LocalizacaoPet;
import com.apipet.domain.port.out.LocalizacaoPersistencePort;
import com.apipet.domain.port.out.PositionStackClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocalizacaoServiceImplTest {

    private LocalizacaoServiceImpl service;
    private LocalizacaoPersistencePort repository;
    private PositionStackClientPort client;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(LocalizacaoPersistencePort.class);
        client = Mockito.mock(PositionStackClientPort.class);
        service = new LocalizacaoServiceImpl(repository, client);
    }

    @Test
    void deveProcessarLocalizacaoComSucesso() {
        LocalizacaoDTO dto = new LocalizacaoDTO(1L, -23.5505, -46.6333, "2023-01-01T10:00:00");

        Endereco endereco = new Endereco("Brasil", "SP", "São Paulo", "Centro", "Av. Paulista");
        LocalizacaoPet esperado = new LocalizacaoPet(1L, -23.5505, -46.6333, "2023-01-01T10:00:00",
                "Brasil", "SP", "São Paulo", "Centro", "Av. Paulista");

        when(client.buscarEndereco(dto.getLatitude(), dto.getLongitude())).thenReturn(endereco);
        when(repository.salvar(any())).thenReturn(esperado);

        LocalizacaoPet resultado = service.processarLocalizacao(dto);

        assertNotNull(resultado);
        assertEquals("Brasil", resultado.getPais());
        verify(client, times(1)).buscarEndereco(dto.getLatitude(), dto.getLongitude());
        verify(repository, times(1)).salvar(any());
    }
}
