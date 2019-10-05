package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.transaction.Transaction;
import com.trickl.model.oanda.instrument.HasInstrument;
import java.util.function.Function;

public class TransactionReader
    implements Function<com.trickl.model.oanda.transaction.Transaction, Transaction> {

  @Override
  public Transaction apply(com.trickl.model.oanda.transaction.Transaction transaction) {
    String instrument = null;
    if (transaction instanceof HasInstrument) {
      instrument = ((HasInstrument) transaction).getInstrument();
    }
    return Transaction.builder()
        .id(transaction.getId())
        .time(transaction.getTime())
        .accountId(transaction.getAccountId())
        .instrumentId(instrument)
        .build();
  }
}
