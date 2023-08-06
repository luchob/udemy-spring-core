package org.example.repository;

import org.example.model.ExRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

abstract class ForexRepositoryBase implements ForexRepository {

    private final List<ExRate> exRates = new ArrayList<>();
    private final Supplier<String> baseCurrencySupplier;

    ForexRepositoryBase(Supplier<String> baseCurrencySupplier) {
        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExchangeRate(String currencyCode) {
        return exRates
                .stream()
                .filter(exRate -> Objects.equals(exRate.currencyCode(), currencyCode))
                .findAny();
    }

    protected void addExRates(List<ExRate> exRates) {
        this.exRates.addAll(exRates);
    }

    protected void addBaseRate() {
        this.exRates.add(asExRate(baseCurrencySupplier.get(), "1"));
    }

    protected ExRate asExRate(String currency, String rate) {
        return new ExRate(currency, new BigDecimal(rate).setScale(5, RoundingMode.CEILING));
    }
}
