package org.example.repository;

import java.util.Optional;
import org.example.model.ExRate;

public interface ExRateRepository {

  Optional<ExRate> findExRate(String currency);

}
