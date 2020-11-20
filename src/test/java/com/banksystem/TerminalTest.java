package com.banksystem;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class TerminalTest {
  public   Account account = new Account("rub" , 10, 7831);



    @Test
  public   void getPinAndNumber(){
        String className = "User";
        try {
            Class accountClass = Class.forName("com.banksystem.Account");
            Constructor constructor = accountClass.getConstructor();
            constructor.newInstance("rub", 10, 7831);
            Field pinCode = accountClass.getDeclaredField("pinCode");
            Field number = accountClass.getDeclaredField("number");



           /* int resultNumber = number.getInt(number);
            int resultPinCode = number.getInt(pinCode);*/

            /*Assert.assertTrue(resultNumber == 10);
            Assert.assertTrue(resultPinCode == 7831);*/

        } catch (ClassNotFoundException e) {
            System.out.println("Не могу найти класс с именем " + className);
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("Не могу найти поле");
            e.printStackTrace();
        }/* catch (IllegalAccessException e) {
            System.out.println("не могу получить значение с поля");
            e.printStackTrace();
        }*/ catch (NoSuchMethodException e) {
            System.out.println("не могу получить конструктор");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("не могу создать экземпляр класса");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}