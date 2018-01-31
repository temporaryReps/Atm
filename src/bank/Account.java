package bank;

import java.math.BigDecimal;
import java.util.Objects;

class Account {
    private long number;
    private int currentCardCvv;
    private String currentCardDate;
    private BigDecimal balance;

    Account(long number, int cvv, String date) {
        this.number = number;
        balance = new BigDecimal(10000);
        currentCardCvv = cvv;
        currentCardDate = date;
    }

    void setAmount(BigDecimal balance) {
        this.balance = balance;
    }

    void put(BigDecimal amount) {
        balance = balance.add(amount);
    }

    boolean withdraw(BigDecimal amount) {
        int difference = balance.compareTo(amount);
        if (difference >= 0) {
            balance = balance.subtract(amount);
            return true;
        } else {
            return false;
        }
    }

    BigDecimal getBalance() {
        return balance;
    }

    long getNumber() {
        return number;
    }

    boolean isEqual(int ccv, String date) {
        return currentCardCvv == ccv && currentCardDate.equals(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number == account.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}