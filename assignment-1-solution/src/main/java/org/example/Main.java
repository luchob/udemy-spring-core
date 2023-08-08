package org.example;

import org.example.service.ForexService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("org.example");

        ctx.registerShutdownHook();

        ForexService forexService = ctx.getBean(ForexService.class);

        String sourceCurrency = args[0];
        String dstCurrency = args[1];
        BigDecimal amount = new BigDecimal(args[2]);

        if (forexService.isSupported(sourceCurrency, dstCurrency)) {
            System.out.println(amount + " " + sourceCurrency + " equals to " +
                    forexService.convert(sourceCurrency, amount, dstCurrency));
        } else {
            System.out.println("Conversion from " + sourceCurrency + " to " +
                    dstCurrency + " is not possible.");
        }

    }
}