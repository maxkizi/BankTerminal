package com.banksystem.domain.accounts.factory;

import com.banksystem.domain.enums.CurrencyType;
import com.banksystem.domain.accounts.Account;

public interface AccountFactory {
    public Account createAccount(CurrencyType currencyType, int number, int pinCode);

}
