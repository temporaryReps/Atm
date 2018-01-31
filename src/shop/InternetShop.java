package shop;

import bank.Bank;

import java.math.BigDecimal;

public class InternetShop {
    public static Bank bankConnect(BigDecimal amount) {
        Bank bank = new Bank();
        bank.setCurrentAmount(amount);

        return bank;
    }
}
