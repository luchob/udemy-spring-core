package org.example;

import org.example.util.CsvForexCalc;
import org.example.util.InMemoryForexCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;

public class Main {

    private static Supplier<String> baseCurrncySupplier = () -> "EUR";

    public static void main(String[] args) {

        // TODO: This code is very ugly. We will need a service here, won't we?
        String sourceCurrency = args[0];
        String targetCurrency = args[1];
        String baseCurrency = baseCurrncySupplier.get();
        BigDecimal amount = new BigDecimal(args[2]);

        CsvForexCalc csvForexCalc = new CsvForexCalc();
        InMemoryForexCalc inMemoryForexCalc = new InMemoryForexCalc();
        // TODO: What about unlimited amount of exrate calculators/repositories?
        BigDecimal result =  null;

        if (!csvForexCalc.isSupported(sourceCurrency, targetCurrency) &&
                !inMemoryForexCalc.isSupported(sourceCurrency, targetCurrency)) {

            BigDecimal srcToBase = null, dstToBase = null;

            if (csvForexCalc.isSupported(sourceCurrency, baseCurrency)) {
                srcToBase = csvForexCalc.convert(sourceCurrency, amount, baseCurrency);
            } else if (inMemoryForexCalc.isSupported(sourceCurrency, baseCurrency)) {
                srcToBase = inMemoryForexCalc.convert(sourceCurrency, amount, baseCurrency);
            }

            if (srcToBase == null) {
                System.out.println("Cannot convert source to base...");
                return;
            }

            //
            if (csvForexCalc.isSupported(targetCurrency, baseCurrency)) {
                dstToBase = csvForexCalc.convert(targetCurrency, amount, baseCurrency);
            } else if (inMemoryForexCalc.isSupported(targetCurrency, baseCurrency)) {
                dstToBase = inMemoryForexCalc.convert(targetCurrency, amount, baseCurrency);
            }

            if (dstToBase == null) {
                System.out.println("Cannot convert target to base...");
                return;
            }

            result = srcToBase.divide(dstToBase, RoundingMode.CEILING).multiply(amount);


        } else if (csvForexCalc.isSupported(sourceCurrency, targetCurrency)) {
            result = csvForexCalc.convert(sourceCurrency, amount, targetCurrency);
        } else if (inMemoryForexCalc.isSupported(sourceCurrency, targetCurrency)) {
            result = inMemoryForexCalc.convert(sourceCurrency, amount, targetCurrency);
        }

        if (result != null) {
            System.out.println(amount + " " +
                    sourceCurrency + " equals to " +
                    result + " " + targetCurrency);
        }

    }
}