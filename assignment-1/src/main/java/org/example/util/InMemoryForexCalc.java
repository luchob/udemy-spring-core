package org.example.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InMemoryForexCalc implements ForexCalc {

    private final Supplier<String> baseCurrencySupplier = () -> "EUR";;

    // TODO: Why not extract these into a repository?
    private final List<ExRate> exRates;

    public InMemoryForexCalc() {
        // TODO: Maybe a postconstruct?
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

    private static ExRate asRate(String currencyCode, String exRate) {
        // leave the new operators
        return new ExRate(currencyCode, new BigDecimal(exRate).setScale(5, RoundingMode.CEILING));
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
