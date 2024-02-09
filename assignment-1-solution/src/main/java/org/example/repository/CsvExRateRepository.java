package org.example.repository;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.example.model.ExRate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CsvExRateRepository extends ExRateRepositoryBase{


  public CsvExRateRepository(@Qualifier(BASE_CURRENCY_SUPPLIER) Supplier<String> baseCurrencySupplier) {
    super(baseCurrencySupplier);
  }

  @PostConstruct
  void init() {
    super.addExRates(
        new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("currencies.csv")))
            .lines()
            .map(this::asExRate)
            .toList()
    );
    super.addBaseCurrencyRate();
  }
}
