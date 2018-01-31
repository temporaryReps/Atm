package atm;

public interface Showable {
    /**
     *
     * @param report sting that will be contained into main label GUI
     */
    void updateUI(String report);

    /**
     * set controller for current view
     * @param controller controller of MVC pattern
     */
    void setController(Object controller);
    /**
     * read data from fields and pass them to controller
     */
    void sendData();
}
