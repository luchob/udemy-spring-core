package org.example.service.impl;

import org.example.repository.ExRateRepository;
import org.example.service.ForexService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ForexServiceImpl implements ForexService {

    private final List<ExRateRepository> allRepos;

    public ForexServiceImpl(List<ExRateRepository> allRepos) {
        this.allRepos = allRepos;
    }

    @Override
    public boolean isSupported(String srcCurrency, String dstCurrency) {
        return false;
    }

    @Override
    public BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency) {
        return null;
    }
}
