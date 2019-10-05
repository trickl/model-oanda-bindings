package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.trade.TradeState;
import java.util.function.Function;

public class TradeStateReader
    implements Function<com.trickl.model.oanda.trade.TradeState, TradeState> {

  @Override
  public TradeState apply(com.trickl.model.oanda.trade.TradeState tradeState) {
    switch (tradeState) {
      case OPEN:
        return TradeState.OPEN;
      case CLOSE_WHEN_TRADEABLE:
        return TradeState.CLOSE_WHEN_TRADEABLE;
      case CLOSED:
        return TradeState.CLOSED;
      default:
        throw new IllegalArgumentException(tradeState.toString());
    }
  }
}
