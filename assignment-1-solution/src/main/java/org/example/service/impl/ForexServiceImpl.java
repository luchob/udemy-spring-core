package org.example.service.impl;

import org.example.model.ExRate;
import org.example.repository.ExRateRepository;
import org.example.service.ForexService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ForexServiceImpl implements ForexService {

    private final List<ExRateRepository> allRepos;

    public ForexServiceImpl(List<ExRateRepository> allRepos) {
        this.allRepos = allRepos;
    }

    @Override
    public boolean isSupported(String srcCurrency, String dstCurrency) {
        return findExRates(srcCurrency, dstCurrency)
                .isPresent();
    }

    @Override
    public BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency) {

        ExRates exRates = findExRates(srcCurrency, dstCurrency)
                .orElseThrow(() -> new IllegalArgumentException("Conversion between " + srcCurrency + " and " +
                        dstCurrency + " is not possible. Use isSupported."));

        return exRates.dst.rate()
                .divide(exRates.src.rate(), RoundingMode.CEILING)
                .multiply(srcAmount);
    }

    private Optional<ExRates> findExRates(String srcCurrency, String dstCurrency) {
        ExRate src = null, dst = null;

        for (ExRateRepository  repo : allRepos) {
            src = repo.findExRate(srcCurrency).orElse(src);
            dst = repo.findExRate(srcCurrency).orElse(dst);

            if (src != null && dst != null) {
                return Optional.of(new ExRates(src, dst));
            }
        }
        return Optional.empty();
    }

    private record ExRates(ExRate src, ExRate dst) {

    }
}
