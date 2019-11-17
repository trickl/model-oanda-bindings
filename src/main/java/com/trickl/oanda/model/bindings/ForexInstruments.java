package com.trickl.oanda.model.bindings;

import com.trickl.model.instrument.CurrencyPair;
import com.trickl.model.instrument.DerivativeType;
import com.trickl.text.oanda.CurrencyPairFormat;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class ForexInstruments {
  public static final CurrencyPair EUR_USD = buildInstrument("EUR", "USD", 107, "Euro");
  public static final CurrencyPair USD_JPY = buildInstrument("USD", "JPY", 106, "Yen");
  public static final CurrencyPair GBP_USD = buildInstrument("GBP", "USD", 105, "Cable");
  public static final CurrencyPair USD_CAD = buildInstrument("USD", "CAD", 104, "Loonie");
  public static final CurrencyPair USD_CHF = buildInstrument("USD", "CHF", 103, "Swissy");
  public static final CurrencyPair AUD_USD = buildInstrument("AUD", "USD", 102, "Aussie");
  public static final CurrencyPair NZD_USD = buildInstrument("NZD", "USD", 101, "Kiwi");

  public static final CurrencyPair EUR_GBP = buildInstrument("EUR", "GBP", 14);
  public static final CurrencyPair EUR_CHF = buildInstrument("EUR", "CHF", 13);
  public static final CurrencyPair EUR_JPY = buildInstrument("EUR", "JPY", 12);
  public static final CurrencyPair GBP_JPY = buildInstrument("GBP", "JPY", 11);
  public static final CurrencyPair CHF_JPY = buildInstrument("CHF", "JPY", 10);
  public static final CurrencyPair GBP_CHF = buildInstrument("GBP", "CHF", 9);
  public static final CurrencyPair EUR_CAD = buildInstrument("EUR", "CAD", 8);
  public static final CurrencyPair EUR_AUD = buildInstrument("EUR", "AUD", 7);
  public static final CurrencyPair EUR_NZD = buildInstrument("EUR", "NZD", 6);
  public static final CurrencyPair GBP_AUD = buildInstrument("GBP", "AUD", 5);
  public static final CurrencyPair GBP_CAD = buildInstrument("GBP", "CAD", 4);
  public static final CurrencyPair CAD_JPY = buildInstrument("CAD", "JPY", 3);
  public static final CurrencyPair AUD_JPY = buildInstrument("AUD", "JPY", 2);
  public static final CurrencyPair NZD_JPY = buildInstrument("NZD", "JPY", 1);

  public static final CurrencyPair EUR_TRY = buildInstrument("EUR", "TRY");
  public static final CurrencyPair USD_TRY = buildInstrument("USD", "TRY");
  public static final CurrencyPair USD_SEK = buildInstrument("USD", "SEK");
  public static final CurrencyPair USD_NOK = buildInstrument("USD", "NOK");
  public static final CurrencyPair USD_DKK = buildInstrument("USD", "DKK");
  public static final CurrencyPair USD_ZAR = buildInstrument("USD", "ZAR");
  public static final CurrencyPair USD_HKD = buildInstrument("USD", "HKD");
  public static final CurrencyPair USD_SGD = buildInstrument("USD", "SGD");
  public static final CurrencyPair USD_CNY = buildInstrument("USD", "CNY");
  public static final CurrencyPair USD_ILS = buildInstrument("USD", "ILS");
  public static final CurrencyPair USD_RUB = buildInstrument("USD", "RUB");
  public static final CurrencyPair USD_SAR = buildInstrument("USD", "SAR");
  public static final CurrencyPair USD_TWD = buildInstrument("USD", "TWD");
  public static final CurrencyPair USD_INR = buildInstrument("USD", "INR");
  public static final CurrencyPair USD_MYR = buildInstrument("USD", "MYR");
  public static final CurrencyPair USD_THB = buildInstrument("USD", "THB");
  public static final CurrencyPair USD_PHP = buildInstrument("USD", "PHP");
  public static final CurrencyPair USD_KWD = buildInstrument("USD", "KWD");
  public static final CurrencyPair USD_ISK = buildInstrument("USD", "ISK");
  public static final CurrencyPair USD_PLN = buildInstrument("USD", "PLN");
  public static final CurrencyPair USD_KRW = buildInstrument("USD", "KRW");
  public static final CurrencyPair USD_IDR = buildInstrument("USD", "IDR");
  public static final CurrencyPair USD_MXN = buildInstrument("USD", "MXN");

  @Getter
  private static final List<CurrencyPair> all = new ArrayList<>();

  private ForexInstruments() {}
  
  public static List<CurrencyPair> major() {
    return all.stream().filter(inmt -> inmt.getRelativeLiquidity() > 100)
        .collect(Collectors.toList());
  }

  /**
   * Get all minor currency pairs.
   */
  public static List<CurrencyPair> minor() {
    return all.stream().filter(
        inmt -> inmt.getRelativeLiquidity() > 0 && inmt.getRelativeLiquidity() <= 100)
        .collect(Collectors.toList());
  }

  /**
   * Get all exotic currency pairs.
   */
  public static List<CurrencyPair> exotics() {
    return all.stream().filter(
        inmt -> inmt.getRelativeLiquidity() <= 0)
        .collect(Collectors.toList());
  }

  private static CurrencyPair buildInstrument(
      String buyCurrencyCode,
      String sellCurrencyCode) {
    return buildInstrument(buyCurrencyCode, sellCurrencyCode, 0);
  }

  private static CurrencyPair buildInstrument(
      String buyCurrencyCode,
      String sellCurrencyCode,
      int relativeLiquidity) {
    return buildInstrument(buyCurrencyCode, sellCurrencyCode, relativeLiquidity, null);
  }

  private static CurrencyPair buildInstrument(
      String buyCurrencyCode,
      String sellCurrencyCode,
      int relativeLiquidity,
      String name) {
    com.trickl.model.oanda.instrument.CurrencyPair currencyPair = 
        new com.trickl.model.oanda.instrument.CurrencyPair(
            Currency.getInstance(buyCurrencyCode),
            Currency.getInstance(sellCurrencyCode)
        );

    String code = CurrencyPairFormat.format(currencyPair, CurrencyPairFormat.OANDA_FORMAT);

    String description = MessageFormat.format("Buy {0}, Sell {1}", 
        currencyPair.getBuyCurrency().getDisplayName(),
        currencyPair.getSellCurrency().getDisplayName());

    CurrencyPair instrument = CurrencyPair.builder()
        .buyCurrency(currencyPair.getBuyCurrency())
        .sellCurrency(currencyPair.getSellCurrency())
        .exchangeId("OANDA")
        .code(code)
        .derivativeType(DerivativeType.SPOT_FOREX)
        .relativeLiquidity(relativeLiquidity)
        .name(name != null ? name : code)
        .description(description)    
        .build();

    all.add(instrument);

    return instrument;
  }
}

