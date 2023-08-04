package org.example.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("baseCurrency")
public class BaseCurrencySupplier implements Supplier<String> {
    @Override
    public String get() {
        return "EUR";
    }
}
