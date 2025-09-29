package com.apipet.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apipet.application.dto.LocalizacaoDTO;
import com.apipet.domain.model.Endereco;
import com.apipet.domain.model.LocalizacaoPet;
import com.apipet.domain.port.in.LocalizacaoUseCase;
import com.apipet.domain.port.out.LocalizacaoPersistencePort;
import com.apipet.domain.port.out.PositionStackClientPort;


@Service
public class LocalizacaoServiceImpl implements LocalizacaoUseCase {

    private final LocalizacaoPersistencePort repository;
    private final PositionStackClientPort positionClient;
    
    public LocalizacaoServiceImpl(LocalizacaoPersistencePort repository,
    								PositionStackClientPort positionClient) {
    	this.repository = repository;
    	this.positionClient = positionClient;
    }

    @Override
    public LocalizacaoPet processarLocalizacao(LocalizacaoDTO dto) {
        Endereco endereco = positionClient.buscarEndereco(dto.getLatitude(), dto.getLongitude());

        LocalizacaoPet pet = new LocalizacaoPet();
        pet.setSensorId(dto.getSensorId());
        pet.setLatitude(dto.getLatitude());
        pet.setLongitude(dto.getLongitude());
        pet.setDataHora(dto.getDataHora());
        pet.setPais(endereco.getCountry());
        pet.setEstado(endereco.getRegion());
        pet.setCidade(endereco.getCity());
        pet.setBairro(endereco.getBairro());
        pet.setEndereco(endereco.getLabel());

        return repository.salvar(pet);
    }

    @Override
    public List<LocalizacaoPet> listarTodas() {
        return repository.listarTodas();
    }

    @Override
    public LocalizacaoPet buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Override
    public boolean removerPorId(Long id) {
        return repository.removerPorId(id);
    }
}
