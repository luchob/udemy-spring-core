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

public class CsvExRateRepository implements ExRateRepository{

    private final Supplier<String> baseCurrencySupplier;
    private List<ExRate> exRates;

    public CsvExRateRepository(Supplier<String> baseCurrencySupplier) {

        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExRate(String currencyCode) {
        return exRates
                .stream()
                .filter(exRate -> Objects.equals(exRate.currencyCode(), currencyCode))
                .findAny();
    }

    @PostConstruct
    void init() {
        exRates.addAll(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("currencies.csv")))
                .lines()
                .map(this::asExRate)
                .toList());

        exRates.add(new ExRate(baseCurrencySupplier.get(), BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING)));

    }

    private ExRate asExRate(String s) {
        var line = s.split(",");
        return new ExRate(line[0].trim(), new BigDecimal(line[1].trim()).setScale(5, RoundingMode.CEILING));
    }
}
