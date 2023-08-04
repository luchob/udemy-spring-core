package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
@Repository
public class InMemoryForexRepository extends ForexRepositoryBase {

    private final Supplier<String> baseCurrencySupplier;
    private final List<ExRate> exRates = new ArrayList<>();

    public InMemoryForexRepository(@Qualifier("baseCurrency") Supplier<String> baseCurrencySupplier) {
        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExchangeRate(String currencyCode) {
        return exRates
                .stream()
                .filter(exRate -> Objects.equals(exRate.currencyCode(), currencyCode))
                .findAny();
    }

    void init() {
        exRates.addAll(
                List.of(
                        asExRate("KRW", "18.7332"),
                        asExRate("MYR", "5.0736"),
                        asExRate("NZD", "1.7888"),
                        asExRate("PHP", "60.590"),
                        asExRate("SGD", "1.4762"),
                        asExRate("THB", "38.159"),
                        asExRate("ZAR", "19.7927"),
                        asExRate("BGN", "1.9558"),
                        asExRate(baseCurrencySupplier.get(), "1")
                )
        );
    }

    private ExRate asExRate(String currency, String rate) {
        return new ExRate(currency, new BigDecimal(rate).setScale(5, RoundingMode.CEILING));
    }

}