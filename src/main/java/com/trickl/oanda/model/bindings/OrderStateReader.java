package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.OrderState;
import java.util.function.Function;

public class OrderStateReader
    implements Function<com.trickl.model.oanda.order.OrderState, OrderState> {

  @Override
  public OrderState apply(com.trickl.model.oanda.order.OrderState orderState) {
    switch (orderState) {
      case PENDING:
        return OrderState.PENDING;
      case TRIGGERED:
        return OrderState.TRIGGERED;
      case FILLED:
        return OrderState.FILLED;
      case CANCELLED:
        return OrderState.CANCELLED;
      default:
        throw new IllegalArgumentException(orderState.toString());
    }
  }
}
