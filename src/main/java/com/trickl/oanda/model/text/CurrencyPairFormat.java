package com.trickl.oanda.model.text;

import com.trickl.model.pricing.instrument.CurrencyPair;
import java.text.MessageFormat;

public class CurrencyPairFormat {

  public static final String OANDA_FORMAT = "{0}_{1}";
  public static final String SIXCHAR_FORMAT = "{0}{1}";

  private CurrencyPairFormat() {}

  /**
   * Format an FX instrument name.
   *
   * @param instrument instrument to format
   * @param format Format to use
   * @return Formatted string
   */
  public static String format(CurrencyPair instrument, String format) {
    MessageFormat messageFormat = new MessageFormat(format);
    return messageFormat.format(
        new Object[] {
          instrument.getBuyCurrency().getCurrencyCode(),
          instrument.getSellCurrency().getCurrencyCode()
        });
  }
}
