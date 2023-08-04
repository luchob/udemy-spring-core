package org.example.service.impl;

import org.example.model.ExRate;
import org.example.repository.ForexRepository;
import org.example.service.ForexService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ForexServiceImpl implements ForexService {

    private final List<ForexRepository> allRepos;

    public ForexServiceImpl(List<ForexRepository> allRepos) {
        this.allRepos = allRepos;
    }

    @Override
    public boolean isSupported(String src, String dst) {
        return findExRates(src, dst).isPresent();
    }

    private Optional<ExRates> findExRates(String src, String dst) {
        ExRate srcExRate = null, dstExRate = null;

        for (ForexRepository repo : allRepos) {
            srcExRate = repo.findExchangeRate(src).orElse(srcExRate);
            dstExRate = repo.findExchangeRate(dst).orElse(dstExRate);

            if (srcExRate != null && dstExRate != null)
                return Optional.of(new ExRates(srcExRate, dstExRate));
        }

        return Optional.empty();
    }

    @Override
    public BigDecimal convert(String src, BigDecimal srcAmount, String dst) {

        ExRates exRates = findExRates(src, dst).orElseThrow(
                () -> new IllegalArgumentException("Conversion between " +
                src + " and " + dst + " is not supported.")
        );

        return exRates.dst().rate().divide(exRates.src().rate(), RoundingMode.CEILING).multiply(srcAmount);
    }

    private record ExRates(ExRate src, ExRate dst) {
    }

}
