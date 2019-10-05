package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.position.Position;
import java.util.function.Function;

public class PositionReader
    implements Function<com.trickl.model.oanda.position.Position, Position> {

  @Override
  public Position apply(com.trickl.model.oanda.position.Position position) {
    return Position.builder()
        .instrumentId(position.getInstrument())
        .longUnits(position.getLongSide().getUnits())
        .longAveragePrice(position.getLongSide().getAveragePrice())
        .shortUnits(position.getShortSide().getUnits())
        .shortAveragePrice(position.getShortSide().getAveragePrice())
        .build();
  }
}
