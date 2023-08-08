package org.example.util;

import org.example.model.ExRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InMemoryForexCalc implements ForexCalc {

    private final Supplier<String> baseCurrencySupplier = () -> "EUR";;

    // TODO: Why not extract these into a repository?


    public InMemoryForexCalc() {
        // TODO: Maybe a postconstruct?

    }

    @Override
    public boolean isSupported(String srcCurrency, String dstCurrency) {
        Set<String> allCodes = exRates
                .stream()
                .map(ExRate::currencyCode)
                .collect(Collectors.toSet());

        return allCodes.contains(srcCurrency) && allCodes.contains(dstCurrency);
    }

    @Override
    public BigDecimal convert(String srcCurrency, BigDecimal amount, String dstCurrency) {
        // TODO: DRY principle?
        var srcExRate = findOrThrow(srcCurrency).rate();
        var dstExRate = findOrThrow(dstCurrency).rate();

        return dstExRate.divide(srcExRate, RoundingMode.CEILING).multiply(amount);
    }



    private ExRate findOrThrow(String currencyCode) {
        // TODO: DRY Principle or do we need it at all?
        return exRates
                .stream()
                .filter(exRate -> exRate.currencyCode().equals(currencyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(currencyCode + " currency is not supported. Please call isSupportedFirst"));
    }
}
