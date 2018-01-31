package card;

public abstract class Card {
    private String serviceEndDate; //для упрощение кода импользовал просто строку
    private String firstName;
    private String surName;
    private int pin;
    private int cvv;
    private long account;

    Card(String firstName, String surName, String pinCode, String svv, String date, String account) {
        this.firstName = firstName;
        this.surName = surName;
        pin = Integer.valueOf(pinCode);
        cvv = Integer.valueOf(svv);
        this.account = Integer.valueOf(account);
        serviceEndDate = date;
    }

    public String getSurName() {
        return surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getPin() {
        return pin;
    }

    public int getCvv() {
        return cvv;
    }

    public long getAccount() {
        return account;
    }

    public String getServiceEndDate() {
        return serviceEndDate;
    }
}
