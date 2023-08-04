package org.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface ForexService {

    boolean isSupported(String src, String dst);

    BigDecimal convert(String src, BigDecimal srcAmount, String dst);

}
