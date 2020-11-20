package com.banksystem;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class TerminalTest {
    User user1 = new User(1, "Кизилов", "Максим");

    @Test
  public   void getPinAndNumber(){
        try {
            Class accountClass = Class.forName("com.banksystem.Account");
            Object account = accountClass.getConstructor(String.class, int.class, int.class).newInstance("rub", 10, 7831);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не могу найти класс");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("Не могу получить конструктор с указанными параметрами");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("проблемы с созданием экземпляра");
        }

    }
}