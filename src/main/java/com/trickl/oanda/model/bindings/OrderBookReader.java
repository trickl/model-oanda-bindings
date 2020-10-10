package com.trickl.oanda.model.bindings;

import com.trickl.model.instrument.CurrencyPair;
import com.trickl.model.oanda.instrument.OrderBookBucket;
import com.trickl.model.pricing.primitives.OrderBook;
import com.trickl.model.pricing.primitives.PriceSource;
import com.trickl.model.pricing.primitives.Quote;
import com.trickl.text.oanda.CurrencyPairFormat;
import java.math.BigDecimal;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderBookReader
    implements Function<com.trickl.model.oanda.instrument.OrderBook, OrderBook> {

  private final CurrencyPair instrument;
  private final String exchangeId;

  @Override
  public OrderBook apply(com.trickl.model.oanda.instrument.OrderBook orderBook) {
    if (orderBook == null || orderBook.getInstrument() == null) {
      return null;
    }

    OrderBook.OrderBookBuilder builder = OrderBook.builder();
    builder.time(orderBook.getTime());

    com.trickl.model.oanda.instrument.CurrencyPair oandaInstrument =
        new com.trickl.model.oanda.instrument.CurrencyPair(
        instrument.getBuyCurrency(),
        instrument.getSellCurrency());

    String instrumentId = CurrencyPairFormat.format(
        oandaInstrument, CurrencyPairFormat.SIXCHAR_FORMAT);
    PriceSource source =
        PriceSource.builder().exchangeId(exchangeId).instrumentId(instrumentId).build();

    BigDecimal mid = orderBook.getPrice();
    if (orderBook.getBuckets() != null) {
      for (OrderBookBucket bucket : orderBook.getBuckets()) {
        if (bucket.getPrice().compareTo(mid) < 0) {
          builder.bid(
              Quote.builder()
                  .price(bucket.getPrice())
                  .volume(bucket.getLongCountPercent().movePointRight(5).longValue())
                  .source(source)
                  .build());
        } else {
          builder.ask(
              Quote.builder()
                  .price(bucket.getPrice())
                  .volume(bucket.getLongCountPercent().movePointRight(5).longValue())
                  .source(source)
                  .build());
        }
      }
    }

    return builder.build();
  }
}
