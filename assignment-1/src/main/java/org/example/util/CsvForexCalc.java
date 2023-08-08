package org.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CsvForexCalc implements ForexCalc {

    // TODO: seems a good candidate for extraction
    private final Supplier<String> baseCurrncySupplier = () -> "EUR";

    // TODO: Maybe extract the storage and retrieval of exrates to a repository
    private final List<ExRate> exRates = new ArrayList<>();
    private final Set<String> supportedCurrencies;


    public CsvForexCalc() {

        // TODO: maybe postconstruct?
        exRates.addAll(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("currencies.csv")))
                        .lines()
                        .map(this::asExRate)
                        .toList());

        exRates.add(new ExRate(baseCurrncySupplier.get(), BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING)));

        supportedCurrencies = exRates.stream().map(ExRate::currencyCode).collect(Collectors.toSet());
    }

    @Override
    public boolean isSupported(String src, String dst) {
        return supportedCurrencies.contains(src) &&
                supportedCurrencies.contains(dst);
    }

    @Override
    public BigDecimal convert(String src, BigDecimal srcAmount, String dst) {

        var srcExRate = findOrThrow(src).rate();
        var dstExRate = findOrThrow(dst).rate();

        return dstExRate.divide(srcExRate, RoundingMode.CEILING).multiply(srcAmount);
    }

    private ExRate findOrThrow(String currencyCode) {
        // TODO: DRY Principle?
        return exRates
                .stream()
                .filter(exRate -> exRate.currencyCode().equals(currencyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(currencyCode + " currency is not supported. Please call isSupportedFirst"));
    }

    private ExRate asExRate(String s) {
        var line = s.split(",");
        return new ExRate(line[0].trim(), new BigDecimal(line[1].trim()).setScale(5, RoundingMode.CEILING));
    }
}
