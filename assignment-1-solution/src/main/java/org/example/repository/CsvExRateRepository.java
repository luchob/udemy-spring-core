package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class CsvExRateRepository extends BaseExRateRepository{
    public CsvExRateRepository(Supplier<String> baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }

    @PostConstruct
    void init() {
        addExRates(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("currencies.csv")))
                .lines()
                .map(BaseExRateRepository::asExRate)
                .toList());
    }
}
