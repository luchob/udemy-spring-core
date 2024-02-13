package org.example.service.impl;

import static org.example.service.impl.ForexServiceImplTest.TestCurrency.*;

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

  private ForexService forexService;

  enum TestCurrency {
    DID, DUD, SPD, GGD, UDD, NED
  }
  //base -> DID
  //1 DID -> 2 DUD (Duke Dollars)
  //1 DID -> 0.5 SPD (Spring Dollars)
  //1 DID -> 1 GGD (Google Guice Dollars)
  //1 DID -> 4 UDD (Udemy Dollars)

  @BeforeEach
  void init() {

    var exRates1 = List.of(
        new ExRate(DUD.name(), new BigDecimal(2).setScale(5, RoundingMode.CEILING)),
        new ExRate(SPD.name(), new BigDecimal("0.5").setScale(5, RoundingMode.CEILING)),
        new ExRate(DID.name(), new BigDecimal(1).setScale(5, RoundingMode.CEILING))
    );
    var exRates2 = List.of(
        new ExRate(GGD.name(), new BigDecimal(1).setScale(5, RoundingMode.CEILING)),
        new ExRate(UDD.name(), new BigDecimal(4).setScale(5, RoundingMode.CEILING)),
        new ExRate(DID.name(), new BigDecimal(1).setScale(5, RoundingMode.CEILING))
    );
    forexService = new ForexServiceImpl(
        List.of(new TestExRateRepository(exRates1), new TestExRateRepository(exRates2))
    );
  }

  @ParameterizedTest(name = "Source, Destination, isSupported: [{0}], [{1}], [{2}].")
  @MethodSource("isSupportedSource")
  void testIsSupported(
      TestCurrency src,
      TestCurrency dst,
      boolean expected) {

    boolean actual = forexService.isSupported(src.name(), dst.name());

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

  @ParameterizedTest(name = "Source, Amount, Dst, Expected: [{0}], [{1}], [{2}], [{3}].")
  @MethodSource("convertSource")
  void testConvert(
      TestCurrency src,
      BigDecimal amount,
      TestCurrency dst,
      BigDecimal expected) {

    BigDecimal actual = forexService.convert(src.name(), amount, dst.name());

    Assertions.assertEquals(expected.doubleValue(), actual.doubleValue(), 0.00001);
  }

  private static Stream<Arguments> convertSource() {

    //1 DID -> 2 DUD (Duke Dollars)
    //1 DID -> 0.5 SPD (Spring Dollars)
    //1 DID -> 1 GGD (Google Guice Dollars)
    //1 DID -> 4 UDD (Udemy Dollars)
    return Stream.of(
        Arguments.of(DUD.name(), bd(2), SPD.name(), bd(0.5)),
        Arguments.of(DUD.name(), bd(2), GGD.name(), bd(1)),
        Arguments.of(DUD.name(), bd(2), UDD.name(), bd(4))

    );
  }

  private static BigDecimal bd(int val) {
    return new BigDecimal(val).setScale(5, RoundingMode.CEILING);
  }

  private static BigDecimal bd(double val) {
    return new BigDecimal(val).setScale(5, RoundingMode.CEILING);
  }


  private record TestExRateRepository(List<ExRate> exRates) implements ExRateRepository {

    @Override
      public Optional<ExRate> findExRate(String currency) {
        return exRates
            .stream()
            .filter(er -> Objects.equals(er.currencyCode(), currency))
            .findAny();
      }
    }
}
