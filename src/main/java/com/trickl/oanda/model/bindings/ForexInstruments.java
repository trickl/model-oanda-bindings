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
  public static final CurrencyPair EUR_USD;
  public static final CurrencyPair USD_JPY;
  public static final CurrencyPair GBP_USD;
  public static final CurrencyPair USD_CAD;
  public static final CurrencyPair USD_CHF;
  public static final CurrencyPair AUD_USD;
  public static final CurrencyPair NZD_USD;

  public static final CurrencyPair EUR_GBP;
  public static final CurrencyPair EUR_CHF;
  public static final CurrencyPair EUR_JPY;
  public static final CurrencyPair GBP_JPY;
  public static final CurrencyPair CHF_JPY;
  public static final CurrencyPair GBP_CHF;
  public static final CurrencyPair EUR_CAD;
  public static final CurrencyPair EUR_AUD;
  public static final CurrencyPair EUR_NZD;
  public static final CurrencyPair GBP_AUD;
  public static final CurrencyPair GBP_CAD;
  public static final CurrencyPair CAD_JPY;
  public static final CurrencyPair AUD_JPY;
  public static final CurrencyPair NZD_JPY;

  public static final CurrencyPair EUR_TRY;
  public static final CurrencyPair USD_TRY;
  public static final CurrencyPair USD_SEK;
  public static final CurrencyPair USD_NOK;
  public static final CurrencyPair USD_DKK;
  public static final CurrencyPair USD_ZAR;
  public static final CurrencyPair USD_HKD;
  public static final CurrencyPair USD_SGD;
  public static final CurrencyPair USD_CNY;
  public static final CurrencyPair USD_ILS;
  public static final CurrencyPair USD_RUB;
  public static final CurrencyPair USD_SAR;
  public static final CurrencyPair USD_TWD;
  public static final CurrencyPair USD_INR;
  public static final CurrencyPair USD_MYR;
  public static final CurrencyPair USD_THB;
  public static final CurrencyPair USD_PHP;
  public static final CurrencyPair USD_KWD;
  public static final CurrencyPair USD_ISK;
  public static final CurrencyPair USD_PLN;
  public static final CurrencyPair USD_KRW;
  public static final CurrencyPair USD_IDR;
  public static final CurrencyPair USD_MXN;

  @Getter private static final List<CurrencyPair> all;

  static {
    all = new ArrayList<>();
    EUR_USD = buildInstrument("EUR", "USD", 107, "Euro");
    USD_JPY = buildInstrument("USD", "JPY", 106, "Yen");
    GBP_USD = buildInstrument("GBP", "USD", 105, "Cable");
    USD_CAD = buildInstrument("USD", "CAD", 104, "Loonie");
    USD_CHF = buildInstrument("USD", "CHF", 103, "Swissy");
    AUD_USD = buildInstrument("AUD", "USD", 102, "Aussie");
    NZD_USD = buildInstrument("NZD", "USD", 101, "Kiwi");

    EUR_GBP = buildInstrument("EUR", "GBP", 14);
    EUR_CHF = buildInstrument("EUR", "CHF", 13);
    EUR_JPY = buildInstrument("EUR", "JPY", 12);
    GBP_JPY = buildInstrument("GBP", "JPY", 11);
    CHF_JPY = buildInstrument("CHF", "JPY", 10);
    GBP_CHF = buildInstrument("GBP", "CHF", 9);
    EUR_CAD = buildInstrument("EUR", "CAD", 8);
    EUR_AUD = buildInstrument("EUR", "AUD", 7);
    EUR_NZD = buildInstrument("EUR", "NZD", 6);
    GBP_AUD = buildInstrument("GBP", "AUD", 5);
    GBP_CAD = buildInstrument("GBP", "CAD", 4);
    CAD_JPY = buildInstrument("CAD", "JPY", 3);
    AUD_JPY = buildInstrument("AUD", "JPY", 2);
    NZD_JPY = buildInstrument("NZD", "JPY", 1);

    EUR_TRY = buildInstrument("EUR", "TRY");
    USD_TRY = buildInstrument("USD", "TRY");
    USD_SEK = buildInstrument("USD", "SEK");
    USD_NOK = buildInstrument("USD", "NOK");
    USD_DKK = buildInstrument("USD", "DKK");
    USD_ZAR = buildInstrument("USD", "ZAR");
    USD_HKD = buildInstrument("USD", "HKD");
    USD_SGD = buildInstrument("USD", "SGD");
    USD_CNY = buildInstrument("USD", "CNY");
    USD_ILS = buildInstrument("USD", "ILS");
    USD_RUB = buildInstrument("USD", "RUB");
    USD_SAR = buildInstrument("USD", "SAR");
    USD_TWD = buildInstrument("USD", "TWD");
    USD_INR = buildInstrument("USD", "INR");
    USD_MYR = buildInstrument("USD", "MYR");
    USD_THB = buildInstrument("USD", "THB");
    USD_PHP = buildInstrument("USD", "PHP");
    USD_KWD = buildInstrument("USD", "KWD");
    USD_ISK = buildInstrument("USD", "ISK");
    USD_PLN = buildInstrument("USD", "PLN");
    USD_KRW = buildInstrument("USD", "KRW");
    USD_IDR = buildInstrument("USD", "IDR");
    USD_MXN = buildInstrument("USD", "MXN");
  }

  private ForexInstruments() {
    // Utility library  
  }

  /** Get all major currencies pairs. */
  public static List<CurrencyPair> major() {
    return all.stream()
        .filter(inmt -> inmt.getRelativeLiquidity() > 100)
        .collect(Collectors.toList());
  }

  /** Get all minor currency pairs. */
  public static List<CurrencyPair> minor() {
    return all.stream()
        .filter(inmt -> inmt.getRelativeLiquidity() > 0 && inmt.getRelativeLiquidity() <= 100)
        .collect(Collectors.toList());
  }

  /** Get all exotic currency pairs. */
  public static List<CurrencyPair> exotics() {
    return all.stream()
        .filter(inmt -> inmt.getRelativeLiquidity() <= 0)
        .collect(Collectors.toList());
  }

  private static CurrencyPair buildInstrument(String buyCurrencyCode, String sellCurrencyCode) {
    return buildInstrument(buyCurrencyCode, sellCurrencyCode, 0);
  }

  private static CurrencyPair buildInstrument(
      String buyCurrencyCode, String sellCurrencyCode, int relativeLiquidity) {
    return buildInstrument(buyCurrencyCode, sellCurrencyCode, relativeLiquidity, null);
  }

  private static CurrencyPair buildInstrument(
      String buyCurrencyCode, String sellCurrencyCode, int relativeLiquidity, String name) {
    com.trickl.model.oanda.instrument.CurrencyPair currencyPair =
        new com.trickl.model.oanda.instrument.CurrencyPair(
            Currency.getInstance(buyCurrencyCode), Currency.getInstance(sellCurrencyCode));

    String code = CurrencyPairFormat.format(currencyPair, CurrencyPairFormat.OANDA_FORMAT);

    String description =
        MessageFormat.format(
            "Buy {0}, Sell {1}",
            currencyPair.getBuyCurrency().getDisplayName(),
            currencyPair.getSellCurrency().getDisplayName());

    CurrencyPair instrument =
        CurrencyPair.builder()
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
