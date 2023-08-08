package org.example.repository;

import org.example.model.ExRate;

import java.util.Optional;

public interface ExRateRepository {

    Optional<ExRate> findExRate(String currencyCode);
}
