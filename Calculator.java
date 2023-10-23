import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Create components
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton equalsButton, clearButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public Calculator() {
        // Initialize frame settings
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Create text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Create buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }

        // Function buttons
        functionButtons = new JButton[6];
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = equalsButton;
        functionButtons[5] = clearButton;

        for (int i = 0; i < 6; i++) {
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            functionButtons[i].addActionListener(this);
        }

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(equalsButton);
        panel.add(clearButton);

        add(panel, BorderLayout.CENTER);

        // Initialize variables
        num1 = 0.0;
        num2 = 0.0;
        result = 0.0;
        operator = ' ';
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            textField.setText(textField.getText() + command);
        } else if (command.charAt(0) == 'C') {
            textField.setText("");
            num1 = 0.0;
            num2 = 0.0;
            result = 0.0;
            operator = ' ';
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0)
                        result = num1 / num2;
                    else
                        textField.setText("Error");
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
            operator = ' ';
        } else {
            if (operator != ' ')
                return;

            operator = command.charAt(0);
            num1 = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
