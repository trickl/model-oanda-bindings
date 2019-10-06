package com.trickl.oanda.model.bindings;

import com.trickl.model.oanda.pricing.common.Price;
import com.trickl.model.oanda.pricing.common.PriceBucket;
import com.trickl.model.pricing.instrument.CurrencyPair;
import com.trickl.model.pricing.primitives.OrderBook;
import com.trickl.model.pricing.primitives.PriceSource;
import com.trickl.model.pricing.primitives.Quote;
import com.trickl.text.oanda.CurrencyPairFormat;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceToOrderBookConverter implements Function<Price, OrderBook> {
  private final String exchangeId;
  private final CurrencyPair instrument;

  @Override
  public OrderBook apply(Price price) {
    OrderBook.OrderBookBuilder builder = OrderBook.builder();
    builder.time(price.getTime());

    com.trickl.model.oanda.instrument.CurrencyPair oandaInstrument =
        new com.trickl.model.oanda.instrument.CurrencyPair(
        instrument.getBuyCurrency(),
        instrument.getSellCurrency());

    String instrumentId = CurrencyPairFormat.format(
        oandaInstrument, CurrencyPairFormat.SIXCHAR_FORMAT);
    PriceSource source =
        PriceSource.builder().exchangeId(exchangeId).instrumentId(instrumentId).build();

    for (PriceBucket bid : price.getBids()) {
      builder.bid(
          Quote.builder().price(bid.getPrice()).volume(bid.getLiquidity()).source(source).build());
    }

    for (PriceBucket ask : price.getAsks()) {
      builder.ask(
          Quote.builder().price(ask.getPrice()).volume(ask.getLiquidity()).source(source).build());
    }
    return builder.build();
  }
}
