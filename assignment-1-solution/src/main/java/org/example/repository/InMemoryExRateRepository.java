package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Supplier;

@Repository
public class InMemoryExRateRepository extends BaseExRateRepository {
    public InMemoryExRateRepository(Supplier<String> baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }
    
    @PostConstruct
    void init() {
        addExRates(List.of(
                asRate("KRW", "18.7332"),
                asRate("MYR", "5.0736"),
                asRate("NZD", "1.7888"),
                asRate("PHP", "60.590"),
                asRate("SGD", "1.4762"),
                asRate("THB", "38.159"),
                asRate("ZAR", "19.7927"),
                asRate("BGN", "1.9558")//e.g. 1 EUR is 1.9558 BGN
                )
        );
        addBaseRate();
    }
}
