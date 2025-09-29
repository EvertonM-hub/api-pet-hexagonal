package com.apipet.infrastructure.adapter.out.persistence;

import com.apipet.domain.model.LocalizacaoPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<LocalizacaoPet, Long> {}

