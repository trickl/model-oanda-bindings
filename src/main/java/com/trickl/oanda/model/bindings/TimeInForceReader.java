package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.orders.TimeInForce;
import java.util.function.Function;

public class TimeInForceReader
    implements Function<com.trickl.model.oanda.order.TimeInForce, TimeInForce> {

  @Override
  public TimeInForce apply(com.trickl.model.oanda.order.TimeInForce timeInForce) {
    switch (timeInForce) {
      case GTC:
        return TimeInForce.GTC;
      case GTD:
        return TimeInForce.GTD;
      case GFD:
        return TimeInForce.GFD;
      case FOK:
        return TimeInForce.FOK;
      case IOC:
        return TimeInForce.IOC;
      default:
        throw new IllegalArgumentException(timeInForce.toString());
    }
  }
}