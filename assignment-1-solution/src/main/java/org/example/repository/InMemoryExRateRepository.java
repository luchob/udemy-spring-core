package org.example.repository;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.function.Supplier;
import org.example.model.ExRate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryExRateRepository extends ExRateRepositoryBase {

  public InMemoryExRateRepository(
      @Qualifier(BASE_CURRENCY_SUPPLIER) Supplier<String> baseCurrencySupplier) {
    super(baseCurrencySupplier);
  }

  @PostConstruct
  void init() {

    super.addExRates(
        List.of(
            ExRate.asExRate("KRW", "18.7332"),
            ExRate.asExRate("MYR", "5.0736"),
            ExRate.asExRate("NZD", "1.7888"),
            ExRate.asExRate("PHP", "60.590"),
            ExRate.asExRate("SGD", "1.4762"),
            ExRate.asExRate("THB", "38.159"),
            ExRate.asExRate("ZAR", "19.7927"),
            ExRate.asExRate("BGN", "1.9558")
        ));

    super.addBaseCurrencyRate();
  }
}
