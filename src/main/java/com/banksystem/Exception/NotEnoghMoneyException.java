package com.banksystem.Exception;

public class NotEnoghMoneyException extends Throwable {
    public NotEnoghMoneyException(){
        super("Недостаточно денег для снятия");
    }
}
