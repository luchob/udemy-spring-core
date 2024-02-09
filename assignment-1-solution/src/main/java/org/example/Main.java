package org.example;

import java.math.BigDecimal;

import org.example.service.ForexService;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        String sourceCurrency = "USD";
        String targetCurrency = "BGN";
        BigDecimal amount = new BigDecimal(1);

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            "org.example"
        );

        ForexService forexService = ctx.getBean(ForexService.class);

        if (forexService.isSupported(sourceCurrency, targetCurrency)) {
            var result = forexService.convert(sourceCurrency, amount, targetCurrency);
            System.out.println(
                amount + " " + sourceCurrency + " equals to " + result + " " + targetCurrency
            );
        }
    }
}