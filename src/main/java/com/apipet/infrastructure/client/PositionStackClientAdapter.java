package com.apipet.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.apipet.domain.model.Endereco;
import com.apipet.domain.model.PositionStackResponse;
import com.apipet.domain.port.out.PositionStackClientPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PositionStackClientAdapter implements PositionStackClientPort {

    @Value("${positionstack.api.key}")
    private String accessKey;

    private final String URL = "http://api.positionstack.com/v1/reverse";

    public Endereco buscarEndereco(double lat, double lon) {
        String uri = String.format("%s?access_key=%s&query=%s,%s", URL, accessKey, lat, lon);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PositionStackResponse> response = restTemplate.getForEntity(uri, PositionStackResponse.class);

        if (response.getBody() == null || response.getBody().getData().isEmpty()) {
            throw new RuntimeException("Nenhum dado retornado pela API PositionStack.");
        }

        return response.getBody().getData().get(0);
    }
}


