package com.banksystem.domain;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.users.User;
import com.banksystem.domain.terminals.Terminal;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public  class TerminalTest {
   public static User user;
   public static Terminal terminal;

    @BeforeClass
    public static void  init(){
        user = new User(1 , "Максим", "Кизилов");
        user.createAccount(CurrencyType.RUBLE, 100100, 7831);
        terminal = new Terminal(user);
    }


    @Test ()
    public void authorize() throws MyAuthorizeException {
        boolean isAuthorized = terminal.authorize(100100, 7831);
        Assert.assertTrue(isAuthorized);
    }

    @Test(expected = MyAuthorizeException.class)
    public void authorizeWithMYAuthorizeException() throws MyAuthorizeException {
        boolean isAuthorized = terminal.authorize(100100, 7831);
        Assert.assertTrue(isAuthorized);
    }

    @Test
    public void getBalance() throws NeedAuthorizationException, MyAuthorizeException {
        boolean isAuthorized = terminal.authorize(100100, 7831);
        Assert.assertEquals(0, terminal.getBalance());
    }

    @Test(expected = NeedAuthorizationException.class)
    public void getBalanceWithNeedAuthorizationException()
            throws NeedAuthorizationException, MyAuthorizeException {
//        terminal.authorize(100100, 7831, "сберегательный счет");
        Assert.assertEquals(0, terminal.getBalance());
    }


    @Test
    public void deposit() throws MyAuthorizeException, NeedAuthorizationException {
        int sum = 0;

        boolean isAuthorized = terminal.authorize(100100, 7831);
        for (int i = 25; i < 1000; i+=25){
            sum += i;
            Assert.assertEquals(sum , terminal.deposit(i));
        }
        System.out.println(terminal.getBalance());
    }

    @Test
    public void withdraw() throws NeedAuthorizationException, MyAuthorizeException, NotEnoughMoneyException {
        boolean isAuthorized = terminal.authorize(100100, 7831);
        terminal.deposit(1000);
        System.out.println();
        terminal.withdraw(10);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawWithNotEnoughMoneyException()
            throws NeedAuthorizationException, MyAuthorizeException, NotEnoughMoneyException {
        boolean isAuthorized = terminal.authorize(100100, 7831);
        terminal.deposit(1000);
        System.out.println();
        terminal.withdraw(1010);
    }
}