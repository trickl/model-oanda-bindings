package com.trickl.model.bindings;

import static com.trickl.assertj.core.api.JsonObjectAssertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.trickl.model.oanda.instrument.Candlestick;
import com.trickl.model.oanda.instrument.OrderBook;
import com.trickl.model.pricing.instrument.CurrencyPair;
import com.trickl.oanda.model.bindings.CandleReader;
import com.trickl.oanda.model.bindings.OrderBookReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Currency;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ConversionTest {

  private static ObjectMapper objectMapper;

  /** Setup the tests. */
  @BeforeAll
  public static void setup() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @ParameterizedTest
  @MethodSource("sourceProvider")
  public void testConversion(Object obj) {
    String outputDirectory = getOutputDirectory().toString();
    assertThat(obj).usingObjectMapper(objectMapper)
        .usingProjectDirectory(outputDirectory).serializesAsExpected();
  }

  static Stream<Object> sourceProvider() {
    return Stream.of(
        new CandleReader()
            .apply(getInputObjectUsingConvention(Candlestick.class)),
        new OrderBookReader(CurrencyPair.builder()
            .buyCurrency(Currency.getInstance("EUR"))
            .sellCurrency(Currency.getInstance("GBP")).build(), "OANDA")
            .apply(getInputObjectUsingConvention(OrderBook.class)));
  }

  private static Path getProjectDirectory() {
    String testPath = ConversionTest.class.getProtectionDomain()
        .getCodeSource().getLocation().getPath();
    return Paths.get(testPath.substring(0, testPath.indexOf("target")),
        "/src/test/resources/");
  }

  private static Path getOutputDirectory() {
    return getProjectDirectory().resolve("output");
  }

  private static Path getInputDirectory() {
    return getProjectDirectory().resolve("input");
  }

  private static <T> T getInputObjectUsingConvention(Class<T> clazz) {
    Path inputDirectory = getInputDirectory();
    Path inputFilePath = inputDirectory.resolve(Paths.get(
        clazz.getPackage().getName().replaceAll("\\.", "/"),
        clazz.getSimpleName() + ".example.json"));
    try (InputStream fileStream = new FileInputStream(inputFilePath.toFile())) {
      return objectMapper.readValue(fileStream, clazz);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
