package com.banksystem.domain.accounts.factory;

import com.banksystem.domain.enums.CurrencyType;
import com.banksystem.domain.accounts.Account;
import com.banksystem.domain.accounts.VtfBankAccount;

public class VtfBankAccountFactory implements AccountFactory{

    @Override
    public Account createAccount(CurrencyType currencyType, int number, int pinCode) {
        return new VtfBankAccount(currencyType, number, pinCode);
    }
}
