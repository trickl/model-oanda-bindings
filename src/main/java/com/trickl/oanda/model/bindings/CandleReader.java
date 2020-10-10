package com.trickl.oanda.model.bindings;

import com.trickl.model.oanda.instrument.Candlestick;
import com.trickl.model.oanda.instrument.CandlestickData;
import com.trickl.model.pricing.primitives.Candle;
import com.trickl.oanda.model.exceptions.ConversionFailureException;
import java.util.function.Function;

public class CandleReader implements Function<Candlestick, Candle> {

  @Override
  public Candle apply(Candlestick candle) {

    CandlestickData ohlc = null;
    if (candle.getMid() != null) {
      ohlc = candle.getMid();
    } else if (candle.getBid() != null) {
      ohlc = candle.getBid();
    } else if (candle.getAsk() != null) {
      ohlc = candle.getAsk();
    }

    if (ohlc == null) {
      throw new ConversionFailureException("Candle missing mid/bid/ask.");
    }

    return Candle.builder().time(
        candle.getTime()).open(ohlc.getOpen()).high(ohlc.getHigh()).low(ohlc.getLow())
        .close(ohlc.getClose()).complete(candle.getComplete()).build();
  }
}
