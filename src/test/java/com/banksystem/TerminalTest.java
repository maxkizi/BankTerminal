package com.banksystem;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.domain.Terminal;
import com.banksystem.domain.User;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TerminalTest extends TestCase {
    User user1;
    Terminal terminal1;

    @BeforeClass
    public void init() {
        user1 = new User(1, "Maxim", "Kizilov");
        user1.createAccount("rub", 100100, 7831, "Rubles");
        terminal1 = new Terminal(user1);
    }


    @Test
    public void testAuthorize() throws MyAuthorizeException {


        boolean result = terminal1.authorize(100100, 7831, "Rubles");
        Assert.assertTrue(result);
    }


}