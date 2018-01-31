package bank;

import java.math.BigDecimal;
import java.util.*;

public class Bank {
    private Map<Client, Set<Account>> clientData;
    private String report;
    private Client currentClient;

    private Account currentAccount;
    private int currentClientSmsCode = -1;

    public BigDecimal currentAmount;

    public Bank() {
        clientData = new TreeMap<>();
        fillData();
    }

    public void setClient(String firstName, String surname) {
        currentClient = new Client(firstName, surname);
    }

    public void put(long accountNumber, BigDecimal amount) {
        HashSet<Account> clientAccounts = (HashSet<Account>) clientData.get(currentClient);
        for (Account a : clientAccounts) {
            if (a.getNumber() == accountNumber) {
                a.put(amount);
                report = "Операция прошла успешно, средства поступили на счёт";
                return;
            }
        }
        operationError();
    }

    public void withdraw(long accountNumber, BigDecimal amount) {
        HashSet<Account> clientAccounts = (HashSet<Account>) clientData.get(currentClient);
        for (Account a : clientAccounts) {
            if (a.getNumber() == accountNumber) {
                if (a.withdraw(amount)) {
                    report = "Операция прошла успешно, средства списаны со счёта \n" +
                            "Списано: " + amount + "\n" +
                            "Доступно: " + a.getBalance();
                } else {
                    report = "Недостаточно средств на счёте";
                }
                return;
            }
        }
        operationError();
    }

    public void showBalance(long accountNumber) {
        Set<Account> clientAccounts = clientData.get(currentClient);
        for (Account a : clientAccounts) {
            if (a.getNumber() == accountNumber) {
                report = "Доступно средств: " + a.getBalance();
                return;
            }
        }
        operationError();
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void writeOff(int code) {
        if (code != -1 && code == currentClientSmsCode) {
            currentAccount.withdraw(currentAmount);
            report = "Оплата произведена успешно, списано " +
                    currentAmount + " co cчёта №" + currentAccount.getNumber();
        } else {
            report = "Неправильно введён проверочныый код";
        }

        currentAccount = null;
    }

    public int findAccount(int cvv, String date) {
        for (Client c : clientData.keySet()) {
            for (Account a : clientData.get(c)) {
                if (a.isEqual(cvv, date)) {
                    currentAccount = a;
                    report = "Введите код полученый в смс";
                    return generateCode();
                }
            }
        }
        report = "Не правильно введены данные, попробуйте повторить операцию.";
        return -1;
    }

    public String getReport() {
        return report;
    }

    private int generateCode() {
        currentClientSmsCode = (int) (Math.random() * 10000);
        return currentClientSmsCode;
    }

    private void fillData() {
        Client ivanov = new Client("Иван", "Иванов");
        Set<Account> ivanovAccounts = new HashSet<>();
        ivanovAccounts.add(new Account(10002222, 132, "18/05/2021"));
        ivanovAccounts.add(new Account(10002223, 485, "03/11/2018"));

        Client petrov = new Client("Пётр", "Петров");
        Set<Account> petrovAccounts = new HashSet<>();
        petrovAccounts.add(new Account(20001111, 874, "27/02/2020"));
        petrovAccounts.add(new Account(20001112, 111, "30/09/2021"));

        clientData.put(ivanov, ivanovAccounts);
        clientData.put(petrov, petrovAccounts);
    }

    private void operationError() {
        report = "Ошибка! Невозможно провести операцию";
    }
}