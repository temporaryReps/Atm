package atm;

import java.io.IOException;
import java.math.BigDecimal;

import bank.Bank;
import card.Card;

public class Atm {
    private Card currentCard;
    private Bank bank;
    private Showable display;
    private String printPath;
    private String cartPath;
    private boolean toPrint; // defines the need to print check

    public Atm(String printPath, String cartPath) {
        bank = new Bank();
        display = new Display();
        display.setController(new Controller(this));

        this.printPath = printPath;
        this.cartPath = cartPath;
    }

    public boolean insertCard(int pin) {
        if (currentCard != null) {
            display.updateUI("Карточка уже в банкомате");
            return true;
        }

        Card temporaryCard = CardScanner.readCard(cartPath);
        if (checkPin(temporaryCard, pin)) {
            currentCard = temporaryCard;
            display.updateUI(CardScanner.getReport());
            return true;
        } else {
            display.updateUI("Пин код неверный");
            return false;
        }
    }

    public void eject() {
        currentCard = null;
        display.updateUI("Заберите вашу карту");
    }

    public void showBalance() {
        if (currentCard == null) {
            noCardShow();
            return;
        }

        bank.setClient(currentCard.getFirstName(), currentCard.getSurName());
        bank.showBalance(currentCard.getAccount());
        display.updateUI(bank.getReport());
        print();
    }

    /**
     *
     * @param amount of money which need to withdraw from user's bank account
     */
    public void withdraw(BigDecimal amount) {
        if (currentCard == null) {
            noCardShow();
            return;
        }

        bank.setClient(currentCard.getFirstName(), currentCard.getSurName());
        bank.withdraw(currentCard.getAccount(), amount);
        display.updateUI(bank.getReport());
        print();
    }


    /**
     *
     * @param toPrint defines the need to print check
     */
    void setToPrint(boolean toPrint) {
        this.toPrint = toPrint;
    }

    /**
     * print check
     */
    private void print() {
        if (!toPrint) {
            return;
        }

        try {
            Printer.print(bank.getReport(), printPath);
        } catch (IOException e) {
            display.updateUI("Печать чека невозможна, банкомат неисправен");
        }
    }

    private void noCardShow() {
        display.updateUI("Вставте пожалуйста вашу карту");
    }

    /**
     * checks pin code for current card
     * @param temporaryCard card
     * @param pin code
     * @return true if code is right
     */
    private boolean checkPin(Card temporaryCard, int pin) {
        return (temporaryCard.getPin() == pin);
    }
}
