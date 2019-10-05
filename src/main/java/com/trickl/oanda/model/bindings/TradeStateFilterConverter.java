package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.trade.TradeStateFilter;
import java.util.function.Function;

public class TradeStateFilterConverter
    implements Function<TradeStateFilter, com.trickl.model.oanda.trade.TradeStateFilter> {

  @Override
  public com.trickl.model.oanda.trade.TradeStateFilter apply(TradeStateFilter tradeStateFilter) {
    switch (tradeStateFilter) {
      case ALL:
        return com.trickl.model.oanda.trade.TradeStateFilter.ALL;
      case CLOSED:
        return com.trickl.model.oanda.trade.TradeStateFilter.CLOSED;
      case CLOSE_WHEN_TRADEABLE:
        return com.trickl.model.oanda.trade.TradeStateFilter.CLOSE_WHEN_TRADEABLE;
      case OPEN:
        return com.trickl.model.oanda.trade.TradeStateFilter.OPEN;
      default:
        throw new IllegalArgumentException(tradeStateFilter.toString());
    }
  }
}
