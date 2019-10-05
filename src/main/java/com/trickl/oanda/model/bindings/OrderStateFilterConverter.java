package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.OrderStateFilter;
import java.util.function.Function;

public class OrderStateFilterConverter
    implements Function<OrderStateFilter, com.trickl.model.oanda.order.OrderStateFilter> {

  @Override
  public com.trickl.model.oanda.order.OrderStateFilter apply(OrderStateFilter orderStateFilter) {
    switch (orderStateFilter) {
      case PENDING:
        return com.trickl.model.oanda.order.OrderStateFilter.PENDING;
      case FILLED:
        return com.trickl.model.oanda.order.OrderStateFilter.FILLED;
      case TRIGGERED:
        return com.trickl.model.oanda.order.OrderStateFilter.TRIGGERED;
      case CANCELLED:
        return com.trickl.model.oanda.order.OrderStateFilter.CANCELLED;
      case ALL:
        return com.trickl.model.oanda.order.OrderStateFilter.ALL;
      default:
        throw new IllegalArgumentException(orderStateFilter.toString());
    }
  }
}
