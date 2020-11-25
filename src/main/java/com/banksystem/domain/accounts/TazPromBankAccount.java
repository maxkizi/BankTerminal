package com.banksystem.domain.accounts;

import com.banksystem.domain.enums.CurrencyType;

public class TazPromBankAccount extends Account{
    public TazPromBankAccount(CurrencyType currencyType, int number, int password) {
        super(currencyType, number, password);
    }

    public TazPromBankAccount() {
    }
}
