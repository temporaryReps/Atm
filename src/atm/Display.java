package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements Showable {
    private Controller controller;

    private JLabel label;
    private JTextField sumField;
    private JPasswordField pinField;
    private JCheckBox printBox;

    Display() {
        super();
        buildGUI();
    }

    @Override
    public void updateUI(String report) {
        label.setText(report);
        pinField.setText(null);
    }

    @Override
    public void setController(Object controller) {
        this.controller = (Controller) controller;
    }

    @Override
    public void sendData() {
        String sum = sumField.getText();
        char[] pin = pinField.getPassword();
        String pinStr = String.copyValueOf(pin);
        boolean print = printBox.isSelected();

        if (sum.equals("")) {
            label.setText("Введите сумму");
        } else if (pinStr.equals("")) {
            label.setText("Введите пин код");
        } else {
            controller.dataBing(Integer.valueOf(sum), Integer.valueOf(pinStr), print);
        }
    }

    private void buildGUI() {
        setTitle("ATM");
        setSize(500, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label = new JLabel("Добро пожаловать!");
        label.setFont(new Font("welcome", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);

        JPanel userPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel sumLabel = new JLabel("Cумма списания:");
        sumLabel.setHorizontalAlignment(JLabel.CENTER);
        sumField = new JTextField(5);

        JLabel pinLabel = new JLabel("Пин код:");
        pinLabel.setHorizontalAlignment(JLabel.CENTER);
        pinField = new JPasswordField(4);

        JLabel print = new JLabel("Печать чека");
        print.setHorizontalAlignment(JLabel.CENTER);
        printBox = new JCheckBox();
        printBox.setSelected(true);

        userPanel.add(sumLabel);
        userPanel.add(sumField);
        userPanel.add(pinLabel);
        userPanel.add(pinField);
        userPanel.add(print);
        userPanel.add(printBox);


        JButton button = new JButton("Ввод");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData();
            }
        });

        JButton ejectButton = new JButton("Извлечь карту");
        ejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.eject();
            }
        });

        setLayout(new BorderLayout(10, 10));
        add(label, BorderLayout.NORTH);
        add(userPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        add(ejectButton, BorderLayout.EAST);

        setVisible(true);
    }
}