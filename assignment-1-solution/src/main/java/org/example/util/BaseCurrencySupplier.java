package org.example.util;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("baseCurrencySupplier")
public class BaseCurrencySupplier implements Supplier<String> {
    @Override
    public String get() {
        return "EUR";
    }
}
