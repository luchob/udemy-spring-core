package org.example.service.impl;

import static org.example.service.impl.ForexServiceImplTest.TestCurrency.DID;
import static org.example.service.impl.ForexServiceImplTest.TestCurrency.DUD;
import static org.example.service.impl.ForexServiceImplTest.TestCurrency.GGD;
import static org.example.service.impl.ForexServiceImplTest.TestCurrency.NED;
import static org.example.service.impl.ForexServiceImplTest.TestCurrency.SPD;
import static org.example.service.impl.ForexServiceImplTest.TestCurrency.UDD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.example.model.ExRate;
import org.example.repository.ExRateRepository;
import org.example.service.ForexService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ForexServiceImplTest {

  private ForexService toTest;

  enum TestCurrency {
    DID, DUD, SPD, UDD, GGD, NED
  }

  // base -> DID (dep. inj dollar)
  // 1 DID -> 2 DUD (Duke dollars)
  // 1 DID -> 0.5 SPD (Spring Dollars)
  // 1 DID -> 1 GGD (Google Guice Dollars)
  // 1 DID -> 4 UDD (Udemy Dollars)

  @BeforeEach
  void init() {

    var exRates1 = List.of(
        ExRate.asExRate(DUD.name(), "2"),
        ExRate.asExRate(SPD.name(), "0.5"),
        ExRate.asExRate(DID.name(), "1")
    );

    var exRates2 = List.of(
        ExRate.asExRate(GGD.name(), "1"),
        ExRate.asExRate(UDD.name(), "4"),
        ExRate.asExRate(DID.name(), "1")
    );

    toTest = new ForexServiceImpl(
        List.of(
            new TestExRateRepository(exRates1),
            new TestExRateRepository(exRates2)
        )
    );
  }

  @ParameterizedTest(name = "Source, Amount, Dst, Expected: [{0}], [{1}], [{2}], [{3}].")
  @MethodSource("convertSource")
  void testConvert(
      TestCurrency src,
      BigDecimal amount,
      TestCurrency dst,
      BigDecimal expected
  ) {
    BigDecimal actual = toTest.convert(src.name(), amount, dst.name());

    Assertions.assertEquals(expected.doubleValue(), actual.doubleValue(), 0.00001);
  }

  private static Stream<Arguments> convertSource() {
    return Stream.of(

        Arguments.of(DUD.name(), bd(2), SPD.name(), bd(0.5)),
        Arguments.of(DUD.name(), bd(2), GGD.name(), bd(1)),
        Arguments.of(DUD.name(), bd(2), UDD.name(), bd(4)),
        Arguments.of(UDD.name(), bd(8), SPD.name(), bd(1))
        // think of more use cases
    );
  }

  private static BigDecimal bd(int val) {
    return new BigDecimal(val).setScale(5, RoundingMode.CEILING);
  }
  private static BigDecimal bd(double val) {
    return new BigDecimal(val).setScale(5, RoundingMode.CEILING);
  }

  @ParameterizedTest(name = "Source, Destination, isSupported: [{0}], [{1}], [{2}].")
  @MethodSource("isSupportedSource")
  void testIsSupported(
      TestCurrency src,
      TestCurrency dst,
      boolean expected
  ) {
    boolean actual = toTest.isSupported(src.name(), dst.name());

    Assertions.assertEquals(expected, actual);
  }

  private static Stream<Arguments> isSupportedSource() {
    return Stream.of(
        Arguments.of(DUD, SPD, true),
        Arguments.of(DUD, DUD, true),
        Arguments.of(DUD, GGD, true),
        Arguments.of(DUD, UDD, true),
        Arguments.of(DUD, NED, false),
        Arguments.of(DUD, DID, true),
        Arguments.of(GGD, SPD, true),
        Arguments.of(GGD, DUD, true),
        Arguments.of(GGD, GGD, true),
        Arguments.of(GGD, UDD, true),
        Arguments.of(GGD, NED, false),
        Arguments.of(GGD, DID, true)
    );

  }

  private record TestExRateRepository(List<ExRate> exRates)
      implements ExRateRepository {

    @Override
    public Optional<ExRate> findExRate(String currency) {
      return exRates
          .stream()
          .filter(er -> Objects.equals(currency, er.currencyCode()))
          .findAny();
    }
  }
}
