package org.example.service;

import java.math.BigDecimal;

public interface ForexService {

    boolean isSupported(String srcCurrency, String dstCurrency);

    BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency);
}
