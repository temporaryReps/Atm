package main;

import atm.Atm;
import atm.Display;
import bank.Bank;
import shop.InternetShop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class Main {
    private static final String PROPERTIES_PATH = "src/printScanFiles.properties";
    private static final String CARD = "card";
    private static final String CHECK = "check";


    public static void main(String[] args) {
        //Для снятия в к банкомате с карточки
        new Main().fromAtm();

        //Для оплаты и интернет магазине c смс подтверждением
//        new Main().shopPaymentWithSms();
    }

    private void fromAtm() {
        Properties properties = new Properties();
        String cardPath = null;
        String checkPath = null;

        try(FileInputStream stream = new FileInputStream(new File(PROPERTIES_PATH))) {
            properties.load(stream);
            cardPath = properties.getProperty(CARD);
            checkPath = properties.getProperty(CHECK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Atm(checkPath, cardPath);
    }

    private void shopPaymentWithSms() {
        int userDisplay; //для эмуляции кода на экране и ввода пользователя

        Bank bank = InternetShop.bankConnect(new BigDecimal(4000.20));
        userDisplay = bank.findAccount(132, "18/05/2021");
        System.out.println("проверочный код - " + userDisplay);

        //вариант ошибочного ввода
//        userDisplay = 1234;
        bank.writeOff(userDisplay);
        System.out.println(bank.getReport());
    }
}
