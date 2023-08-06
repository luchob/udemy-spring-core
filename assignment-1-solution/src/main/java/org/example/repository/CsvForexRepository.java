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

    public CsvForexRepository(@Qualifier("baseCurrency") Supplier<String> baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }

    @PostConstruct
    void init() {
        addExRates(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("currencies.csv")))
                .lines()
                .map(this::asExRate)
                .toList());

        addBaseRate();
    }

    private ExRate asExRate(String s) {
        var line = s.split(",");
        return asExRate(line[0].trim(), line[1].trim());
    }

}
