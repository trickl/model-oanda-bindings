package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.Order;
import java.util.function.Function;

public class OrderConverter implements Function<
    Order, com.trickl.model.oanda.order.Order> {

  @Override
  public com.trickl.model.oanda.order.Order apply(Order order) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
