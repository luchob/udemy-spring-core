package org.example.model;

import java.math.BigDecimal;

public record ExRate(String currencyCode, BigDecimal rate) {
}
