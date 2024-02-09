package org.example.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import org.example.model.ExRate;
import org.example.repository.ExRateRepository;
import org.example.service.ForexService;
import org.springframework.stereotype.Service;

@Service
public class ForexServiceImpl implements ForexService {

  private final List<ExRateRepository> exRateRepositories;

  public ForexServiceImpl(List<ExRateRepository> exRateRepositories) {
    this.exRateRepositories = exRateRepositories;
  }

  @Override
  public boolean isSupported(String srcCurrency, String dstCurrency) {
    return findExRates(srcCurrency, dstCurrency).isPresent();
  }

  @Override
  public BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency) {
    ExRates exRates = findExRates(srcCurrency, dstCurrency)
        .orElseThrow(() -> new IllegalArgumentException(
            "The conversion between" + srcCurrency + "and" + dstCurrency
                + " is not possible. Please use isSupported method first."));

    return exRates.dstRate.rate().divide(exRates.srcRate.rate(), RoundingMode.CEILING)
        .multiply(srcAmount);
  }

  private Optional<ExRates> findExRates(String srcCurrency, String dstCurrency) {
    ExRate srcRate = null, dstRate = null;

    for (ExRateRepository exRateRepository : exRateRepositories) {
      if (srcRate == null) {
        srcRate = exRateRepository.findExRate(srcCurrency).orElse(null);
      }
      if (dstRate == null) {
        dstRate = exRateRepository.findExRate(dstCurrency).orElse(null);
      }

      if (srcRate != null && dstRate != null) {
        return Optional.of(new ExRates(srcRate, dstRate));
      }
    }
    return Optional.empty();
  }

  private record ExRates(ExRate srcRate, ExRate dstRate) {

  }
}
