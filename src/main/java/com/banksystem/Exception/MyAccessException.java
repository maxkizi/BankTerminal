package com.banksystem.Exception;

public class MyAccessException  extends  Exception{
    public MyAccessException(){
        super("Отказано в доступе к банковскому счету\n" +
                "Возможно введен неверный логин(номер)/пароль");
    }
}
