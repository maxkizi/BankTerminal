package com.banksystem.domain.terminals;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.SderBankAccount;
import com.banksystem.domain.accounts.TazPromBankAccount;
import com.banksystem.domain.users.User;

public class VtfBankTerminal extends Terminal {
    public static final int PERCENT_FOR_SDERBANK = 8;
    public static final int PERCENT_FOR_TAZPROM = 3;

    public VtfBankTerminal(User user) {
        super(user);
    }

    public VtfBankTerminal() {
    }

    @Override
    public int deposit(int amount) throws NeedAuthorizationException {
        int rememberAmount = amount;
        if (successfulAuthorization) {
            if (currentAccount instanceof SderBankAccount) {
                calcCommission(amount, PERCENT_FOR_SDERBANK);
                currentAccount.deposit(amount -= bankCommission);
            } else if (currentAccount instanceof TazPromBankAccount) {
                calcCommission(amount, PERCENT_FOR_TAZPROM);
                currentAccount.deposit(amount -= bankCommission);
            } else
                currentAccount.deposit(amount);
            printResultOfOperation(rememberAmount, "Внесено");
            return currentAccount.getBalance();

        } else throw new NeedAuthorizationException();
    }

    @Override
    public int withdraw(int amount) throws NotEnoughMoneyException, NeedAuthorizationException {
        int rememberAmount = amount;
        if (currentAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        } else if (!successfulAuthorization) {
            throw new NeedAuthorizationException();
        } else {
            if (currentAccount instanceof SderBankAccount) {
                calcCommission(amount, PERCENT_FOR_SDERBANK);
                currentAccount.withdraw(amount += bankCommission);
            } else if (currentAccount instanceof TazPromBankAccount) {
                calcCommission(amount, PERCENT_FOR_TAZPROM);
                currentAccount.withdraw(amount += bankCommission);
            } else
                currentAccount.withdraw(amount);
            printResultOfOperation(rememberAmount, "Выдано");
            return rememberAmount;
        }
    }
}
