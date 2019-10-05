package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.account.Account;
import java.util.function.Function;

public class AccountReader
    implements Function<com.trickl.model.oanda.account.Account, Account> {

  @Override
  public Account apply(com.trickl.model.oanda.account.Account account) {

    return Account.builder().build();
  }
}
