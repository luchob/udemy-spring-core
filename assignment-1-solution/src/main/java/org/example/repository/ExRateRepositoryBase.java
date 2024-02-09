package org.example.repository;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import org.example.model.ExRate;
import org.springframework.beans.factory.annotation.Qualifier;

public class ExRateRepositoryBase implements ExRateRepository {

  private final List<ExRate> exRates = new ArrayList<>();
  private final Supplier<String> baseCurrencySupplier;

  public ExRateRepositoryBase(@Qualifier(BASE_CURRENCY_SUPPLIER) Supplier<String> baseCurrencySupplier) {
    this.baseCurrencySupplier = baseCurrencySupplier;
  }

  @Override
  public Optional<ExRate> findExRate(String currency) {
    return exRates
        .stream()
        .filter(er -> Objects.equals(currency, er.currencyCode()))
        .findAny();
  }

  protected void addExRates(Collection<ExRate> exRateCollection) {
    exRates.addAll(exRateCollection);
  }

  protected void addBaseCurrencyRate() {
    exRates.add(new ExRate(baseCurrencySupplier.get(), BigDecimal.valueOf(1).setScale(5, RoundingMode.CEILING)));
  }

  protected ExRate asExRate(String s) {
    var line = s.split(",");
    return asExRate(line[0].trim(), line[1].trim());
  }
  protected static ExRate asExRate(String currencyCode, String exRate) {
    // leave the new operators
    return new ExRate(currencyCode, new BigDecimal(exRate).setScale(5, RoundingMode.CEILING));
  }

}
