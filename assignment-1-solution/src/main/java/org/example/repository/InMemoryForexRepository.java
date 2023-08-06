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

    public InMemoryForexRepository(@Qualifier("baseCurrency") Supplier<String> baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }

    @PostConstruct
    void init() {
        addExRates(
                List.of(
                        asExRate("KRW", "18.7332"),
                        asExRate("MYR", "5.0736"),
                        asExRate("NZD", "1.7888"),
                        asExRate("PHP", "60.590"),
                        asExRate("SGD", "1.4762"),
                        asExRate("THB", "38.159"),
                        asExRate("ZAR", "19.7927"),
                        asExRate("BGN", "1.9558")
                )
        );

        addBaseRate();

    }
}