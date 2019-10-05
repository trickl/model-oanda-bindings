package com.trickl.oanda.model.bindings;

import com.trickl.model.broker.account.Account;
import java.util.function.Function;

public class AccountPropertiesReader
    implements Function<com.trickl.model.oanda.account.AccountProperties, Account> {

  @Override
  public Account apply(com.trickl.model.oanda.account.AccountProperties account) {

    return Account.builder().build();
  }
}
