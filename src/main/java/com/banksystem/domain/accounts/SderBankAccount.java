package com.banksystem.domain.accounts;

import com.banksystem.domain.enums.CurrencyType;

public class SderBankAccount extends Account {
    public SderBankAccount(CurrencyType currencyType, int number, int password) {
        super(currencyType, number, password);
    }

    public SderBankAccount() {
    }
}
