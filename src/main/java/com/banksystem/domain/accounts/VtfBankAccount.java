package com.banksystem.domain.accounts;

import com.banksystem.domain.enums.CurrencyType;

public class VtfBankAccount extends Account {
    public VtfBankAccount(CurrencyType currencyType, int number, int password) {
        super(currencyType, number, password);
    }

    public VtfBankAccount() {
    }

}
