package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.Order;
import com.trickl.model.oanda.instrument.HasInstrument;
import com.trickl.model.oanda.order.HasFilledTime;
import com.trickl.model.oanda.order.HasPrice;
import com.trickl.model.oanda.order.HasTimeInForce;
import com.trickl.model.oanda.order.HasUnits;
import com.trickl.model.oanda.order.TimeInForce;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Function;

public class OrderReader implements Function<com.trickl.model.oanda.order.Order, Order> {

  @Override
  public Order apply(com.trickl.model.oanda.order.Order order) {
    String instrument = null;
    if (order instanceof HasInstrument) {
      instrument = ((HasInstrument) order).getInstrument();
    }

    TimeInForce timeInForce = null;
    if (order instanceof HasTimeInForce) {
      timeInForce = ((HasTimeInForce) order).getTimeInForce();
    }

    Instant filledTime = null;
    if (order instanceof HasFilledTime) {
      filledTime = ((HasFilledTime) order).getFilledTime();
    }

    BigDecimal units = null;
    if (order instanceof HasUnits) {
      units = ((HasUnits) order).getUnits();
    }

    BigDecimal price = null;
    if (order instanceof HasPrice) {
      price = ((HasPrice) order).getPrice();
    }
    
    com.trickl.model.oanda.order.OrderType type =
        com.trickl.model.oanda.order.OrderType.fromOrderClass(order.getClass());

    return Order.builder()
        .id(order.getId())
        .createdAtTime(order.getCreateTime())
        .state(new OrderStateReader().apply(order.getState()))
        .instrumentId(instrument)
        .price(price)
        .quantity(units)
        .timeInForce(new TimeInForceReader().apply(timeInForce))
        .type(new OrderTypeReader().apply(type))
        .lastModifiedTime(filledTime)
        .build();
  }
}
