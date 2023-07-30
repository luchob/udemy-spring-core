package org.example.util;

import java.math.BigDecimal;

public interface ForexCalc {
    boolean isSupported(String src, String dst);

    BigDecimal convert(String src, BigDecimal srcAmount, String dst);
}
