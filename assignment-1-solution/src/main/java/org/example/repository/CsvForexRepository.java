package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Supplier;

@Repository
public class CsvForexRepository extends ForexRepositoryBase {

    private final Supplier<String> baseCurrencySupplier;
    private final List<ExRate> exRates = new ArrayList<>();

    public CsvForexRepository(@Qualifier("baseCurrency") Supplier<String> baseCurrencySupplier) {
        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExchangeRate(String currencyCode) {
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

        exRates.add(new ExRate(baseCurrencySupplier.get(),
                BigDecimal.ONE.setScale(5, RoundingMode.CEILING)));
    }

    private ExRate asExRate(String s) {
        var line = s.split(",");
        return new ExRate(line[0].trim(), new BigDecimal(line[1].trim()).setScale(5, RoundingMode.CEILING));
    }

}
