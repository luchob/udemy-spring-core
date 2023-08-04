package org.example;

import org.example.service.ForexService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;

public class Main {

    private static Supplier<String> baseCurrncySupplier = () -> "EUR";

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("org.example");

        ctx.registerShutdownHook();

        ForexService forexService = ctx.getBean(ForexService.class);

        String sourceCurrency = args[0];
        String targetCurrency = args[1];
        BigDecimal amount = new BigDecimal(args[2]);


        if (forexService.isSupported(sourceCurrency, targetCurrency)) {
            System.out.println(amount + " " +
                    sourceCurrency + " equals to " +
                    forexService.convert(sourceCurrency, amount, targetCurrency) + " " + targetCurrency);
        } else {
            System.out.println("Conversion from  " + sourceCurrency +
                    " to "  + targetCurrency + " is not supported.");
        }

    }
}