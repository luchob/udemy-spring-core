package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class InMemoryExRateRepository implements ExRateRepository{

    private final Supplier<String> baseCurrencySupplier;
    private List<ExRate> exRates;

    public InMemoryExRateRepository(Supplier<String> baseCurrencySupplier) {

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
        exRates = List.of(
                asRate("KRW", "18.7332"),
                asRate("MYR", "5.0736"),
                asRate("NZD", "1.7888"),
                asRate("PHP", "60.590"),
                asRate("SGD", "1.4762"),
                asRate("THB", "38.159"),
                asRate("ZAR", "19.7927"),
                asRate("BGN", "1.9558"),//e.g. 1 EUR is 1.9558 BGN
                asRate(baseCurrencySupplier.get(), "1"));
    }

    private static ExRate asRate(String currencyCode, String exRate) {
        // leave the new operators
        return new ExRate(currencyCode, new BigDecimal(exRate).setScale(5, RoundingMode.CEILING));
    }
}
