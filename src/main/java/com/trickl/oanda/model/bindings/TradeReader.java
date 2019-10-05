package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.trade.Trade;
import java.util.function.Function;

public class TradeReader implements Function<com.trickl.model.oanda.trade.Trade, Trade> {

  @Override
  public Trade apply(com.trickl.model.oanda.trade.Trade trade) {
    return Trade.builder()
      .id(trade.getId())
      .price(trade.getPrice())
      .openTime(trade.getOpenTime())
      .state(new TradeStateReader().apply(trade.getState()))
      .initialUnits(trade.getInitialUnits())
      .currentUnits(trade.getCurrentUnits())      
      .averageClosePrice(trade.getAverageClosePrice())
      .closeTime(trade.getCloseTime())
      .build();
  }
}