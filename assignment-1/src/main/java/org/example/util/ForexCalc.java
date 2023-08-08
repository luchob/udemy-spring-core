package org.example.util;

import java.math.BigDecimal;

public interface ForexCalc {
    boolean isSupported(String srcCurrency, String dstCurrency);

    BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency);
}
