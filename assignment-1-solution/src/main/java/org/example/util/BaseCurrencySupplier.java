package org.example.util;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(BASE_CURRENCY_SUPPLIER)
public class BaseCurrencySupplier implements Supplier<String> {
  public static final String BASE_CURRENCY_SUPPLIER = "baseCurrencySupplier";
  private final String baseCurrency;

  public BaseCurrencySupplier(@Value("${base.currency}") String baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  @Override
  public String get() {
    return baseCurrency;
  }
}
