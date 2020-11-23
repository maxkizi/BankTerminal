package com.banksystem.Exception;

public class NotEnoughMoneyException extends Throwable {
    public NotEnoughMoneyException(){
        super("Недостаточно денег для снятия");
    }
}
