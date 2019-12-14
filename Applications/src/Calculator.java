/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class Calculator extends JFrame implements ActionListener, KeyListener{
    
    private JLabel resultField;
    private HashMap<JButton, String> numberButtons;
    private HashMap<JButton, String> operatorButtons;
    private JButton clear;
    private Calculations calculations;
    private Font font = new Font("Arial", Font.BOLD, 20);


    public Calculator(){

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(300, 300);
        setTitle("Calculator");

        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);

        resultField = new JLabel("0");
        resultField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setFont(font);
        addComponentToContainer(resultField, container, 0, 0, 4, 1);

        numberButtons = new HashMap<>();
        operatorButtons = new HashMap<>();

        calculations = new Calculations(this);

        // Buttons of calculator initialization
        {
            JButton seven = buttonInit("7");
            numberButtons.put(seven, "7");
            addComponentToContainer(seven, container, 0, 1, 1, 1);

            JButton eight = buttonInit("8");
            numberButtons.put(eight, "8");
            addComponentToContainer(eight, container, 1, 1, 1, 1);

            JButton nine = buttonInit("9");
            numberButtons.put(nine, "9");
            addComponentToContainer(nine, container, 2, 1, 1, 1);

            JButton plus = buttonInit("+");
            operatorButtons.put(plus, "+");
            addComponentToContainer(plus, container, 3, 1, 1, 1);

            JButton four = buttonInit("4");
            numberButtons.put(four, "4");
            addComponentToContainer(four, container, 0, 2, 1, 1);

            JButton five = buttonInit("5");
            numberButtons.put(five, "5");
            addComponentToContainer(five, container, 1, 2, 1, 1);

            JButton six = buttonInit("6");
            numberButtons.put(six, "6");
            addComponentToContainer(six, container, 2, 2, 1, 1);

            JButton minus = buttonInit("-");
            operatorButtons.put(minus, "-");
            addComponentToContainer(minus, container, 3, 2, 1, 1);

            JButton one = buttonInit("1");
            numberButtons.put(one, "1");
            addComponentToContainer(one, container, 0, 3, 1, 1);

            JButton two = buttonInit("2");
            numberButtons.put(two, "2");
            addComponentToContainer(two, container, 1, 3, 1, 1);

            JButton three = buttonInit("3");
            numberButtons.put(three, "3");
            addComponentToContainer(three, container, 2, 3, 1, 1);

            JButton multiply = buttonInit("*");
            operatorButtons.put(multiply, "*");
            addComponentToContainer(multiply, container, 3, 3, 1, 1);

            clear = buttonInit("CE");
            clear.setMargin(new Insets(0, 0, 0, 0));
            addComponentToContainer(clear, container, 0, 4, 1, 1);

            JButton zero = buttonInit("0");
            numberButtons.put(zero, "0");
            addComponentToContainer(zero, container, 1, 4, 1, 1);

            JButton dot = buttonInit(".");
            numberButtons.put(dot, ".");
            addComponentToContainer(dot, container, 2, 4, 1, 1);

            JButton div = buttonInit("/");
            operatorButtons.put(div, "/");
            addComponentToContainer(div, container, 3, 4, 1, 1);

            JButton result = buttonInit("=");
            operatorButtons.put(result, "=");
            addComponentToContainer(result, container, 0, 5, 8, 1);
        }
        setVisible(true);
    }

    private void addComponentToContainer(JComponent component, Container container, int x, int y, int dx, int dy){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = dx;
        constraints.gridheight = dy;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;

        container.add(component, constraints);
    }
    
    private JButton buttonInit(String info){

        JButton button = new JButton(info);
        button.setFont(font);
        button.addActionListener(this);
        button.addKeyListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JButton button=(JButton)event.getSource();
        if (numberButtons.containsKey(button)){
            calculations.handleButtonsInput(numberButtons.get(button));
        } else if (operatorButtons.containsKey(button)){
            calculations.handleButtonsInput(operatorButtons.get(button));
        } else if (button.equals(clear)){
            calculations.handleButtonsInput("CE");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        String s = String.valueOf(e.getKeyChar());
        if (operatorButtons.containsValue(s)){
            calculations.handleButtonsInput(s);
        } else if (numberButtons.containsValue(s)){
            calculations.handleButtonsInput(s);
        } if (s.equals("\n")){
            calculations.handleButtonsInput("=");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JLabel getResultField() {
        return resultField;
    }

    public HashMap<JButton, String> getNumberButtons() {
        return numberButtons;
    }

    public HashMap<JButton, String> getOperatorButtons() {
        return operatorButtons;
    }

}
