package org.example.service.repository;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.example.model.ExRate;
import org.example.repository.ExRateRepositoryBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExRateRepositoryBaseTest {
  private static final String BASE_CURRENCY = "DUD";
  private static final String CURRENCY_ONE = "DID";
  private static final BigDecimal CURRENCY_ONE_RATE = new BigDecimal("1.1");
  private static final String CURRENCY_TWO = "SPD";
  private static final BigDecimal CURRENCY_TWO_RATE = new BigDecimal("2.1");
  private static final String FAULTY_CURRENCY = "XYZ";
  private TestRepository toTest;

  @BeforeEach
  void init() {
    toTest = new TestRepository(() -> BASE_CURRENCY);
    toTest.init();
  }

  @ParameterizedTest(name = "Currency, Expected Rate")
  @MethodSource("findRateSource")
  void testConvert(
      String currency,
      BigDecimal expected) {

    Optional<ExRate> rate = toTest.findExRate(currency);

    if (expected == null) {
      Assertions.assertTrue(rate.isEmpty());
    } else {
      Assertions.assertTrue(rate.isPresent());
      Assertions.assertEquals(expected, rate.get().rate());
      Assertions.assertEquals(currency, rate.get().currencyCode());
    }
  }


  private static Stream<Arguments> findRateSource() {

    return Stream.of(
        Arguments.of(CURRENCY_ONE, CURRENCY_ONE_RATE),
        Arguments.of(CURRENCY_TWO, CURRENCY_TWO_RATE),
        Arguments.of(BASE_CURRENCY, new BigDecimal(1).setScale(5, RoundingMode.CEILING)),
        Arguments.of(FAULTY_CURRENCY, null)

    );
  }

  static class TestRepository extends ExRateRepositoryBase {
    public TestRepository(Supplier<String> baseCurrencySupplier) {
      super(baseCurrencySupplier);
    }

    void init() {
      super.addExRates(
          List.of(
              new ExRate(CURRENCY_ONE, CURRENCY_ONE_RATE),
              new ExRate(CURRENCY_TWO, CURRENCY_TWO_RATE)
          ));
      super.addBaseCurrencyRate();
    }
  }

}
