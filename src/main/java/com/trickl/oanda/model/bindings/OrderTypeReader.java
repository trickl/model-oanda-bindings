package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.OrderType;
import java.util.function.Function;

public class OrderTypeReader
    implements Function<com.trickl.model.oanda.order.OrderType, OrderType> {

  @Override
  public OrderType apply(com.trickl.model.oanda.order.OrderType orderState) {
    switch (orderState) {
      case MARKET:
        return OrderType.MARKET;
      case LIMIT:
        return OrderType.LIMIT;
      case STOP:
        return OrderType.STOP;
      case MARKET_IF_TOUCHED:
        return OrderType.MARKET_IF_TOUCHED;
      case TAKE_PROFIT:
        return OrderType.TAKE_PROFIT;
      case TRAILING_STOP_LOSS:
        return OrderType.TRAILING_STOP_LOSS;        
      case FIXED_PRICE:
        return OrderType.FIXED_PRICE;
      default:
        throw new IllegalArgumentException(orderState.toString());
    }
  }
}