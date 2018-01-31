package atm;

import java.math.BigDecimal;

class Controller {
    private Atm atm;

    Controller(Atm atm) {
        this.atm = atm;
    }

    /**
     *
     * @param sum amount os money that user wants to get
     * @param pin pin code is entered by user
     * @param isSelected defines the need to print check
     */
    void dataBing(int sum, int pin, boolean isSelected) {
        if (!atm.insertCard(pin)) {
            return;
        }
        atm.setToPrint(isSelected);
        atm.withdraw(new BigDecimal(sum));
    }

    /**
     * eject card from atm
     */
    void eject() {
        atm.eject();
    }
}
