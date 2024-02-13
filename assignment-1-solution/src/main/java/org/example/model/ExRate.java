package org.example.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ExRate(String currencyCode, BigDecimal rate) {
  public static ExRate asExRate(String currencyCode, String exRate) {
    // leave the new operators
    return new ExRate(currencyCode, new BigDecimal(exRate).setScale(5, RoundingMode.CEILING));
  }
}
