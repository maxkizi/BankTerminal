package com.banksystem.domain.terminals;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.SderBankAccount;

import com.banksystem.domain.users.User;



public class SderBankTerminal extends Terminal {
    public static final int PERCENT_FOR_OTHERS_BANKS = 10;

    public SderBankTerminal(User user) {
        super(user);
    }

    public SderBankTerminal() {
    }


    @Override
    public int deposit(int amount) throws MyAuthorizeException, NeedAuthorizationException {
        if (successfulAuthorization) {
            if (currentAccount instanceof SderBankAccount) {
                currentAccount.deposit(amount);
            } else {
                calcCommission(amount, PERCENT_FOR_OTHERS_BANKS);
                currentAccount.deposit(amount -= bankCommission);
            }
            printResultOfOperation(amount+=bankCommission, "Внесено");
            return currentAccount.getBalance();
        } else throw new MyAuthorizeException();
    }

    @Override
    public int withdraw(int amount) throws NotEnoughMoneyException, MyAuthorizeException, NeedAuthorizationException {
        if (currentAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        } else if (!successfulAuthorization) {
            throw new MyAuthorizeException();
        } else {

            if (currentAccount instanceof SderBankAccount) {
                currentAccount.withdraw(amount);
            } else {
                calcCommission(amount, PERCENT_FOR_OTHERS_BANKS);
                amount += bankCommission;
                currentAccount.withdraw(amount);
                amount-=bankCommission;
            }
            printResultOfOperation(amount, "Выдано");
            return amount;
        }
    }
}
