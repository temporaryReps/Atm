package bank;

import java.util.Objects;

public class Client implements Comparable<Client> {
    private String firstName;
    private String surname;

    Client(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(Client o) {
        return surname.compareTo(o.getSurname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }

    @Override
    public String toString() {
        return firstName + " " + surname;
    }
}
