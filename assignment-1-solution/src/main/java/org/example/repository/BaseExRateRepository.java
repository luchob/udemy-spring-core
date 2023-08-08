package org.example.repository;

import org.example.model.ExRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Supplier;

class BaseExRateRepository implements ExRateRepository{
    private final Supplier<String> baseCurrencySupplier;
    private List<ExRate> exRates = new ArrayList<>();

    BaseExRateRepository(Supplier<String> baseCurrencySupplier) {

        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExRate(String currencyCode) {
        return exRates
                .stream()
                .filter(exRate -> Objects.equals(exRate.currencyCode(), currencyCode))
                .findAny();
    }

    protected void addExRates(Collection<ExRate> exRates) {
        this.exRates.addAll(exRates);
    }

    protected void addBaseRate() {
        exRates.add(new ExRate(baseCurrencySupplier.get(), BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING)));
    }

    protected static ExRate asRate(String currencyCode, String exRate) {
        // leave the new operators
        return new ExRate(currencyCode, new BigDecimal(exRate).setScale(5, RoundingMode.CEILING));
    }

    protected static ExRate asExRate(String s) {
        var line = s.split(",");
        return asRate(line[0].trim(), line[1].trim());
    }
}
