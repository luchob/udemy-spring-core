package org.example.repository;

import org.example.model.ExRate;

import java.util.Optional;

public interface ForexRepository {

    Optional<ExRate> findExchangeRate(String currencyCode);

}
