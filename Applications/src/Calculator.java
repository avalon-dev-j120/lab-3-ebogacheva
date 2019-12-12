/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.*;


public class Calculator extends JFrame implements ActionListener{
    
    private JLabel resultField;
   
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    
    private JButton plus;
    private JButton minus;
    private JButton multiply;
    private JButton div;
    
    private JButton clear;
    private JButton dot;
    private JButton result;
    private JButton zero;
    private Font font = new Font("Arial", Font.BOLD, 24);

    private HashMap<JButton, String> numberButtons;
    private HashMap<JButton, String> operatorButtons;

    private String buffer1 = "0";
    private String buffer2 = "";
    private String operator;
    private boolean operatorSet = false;
    private boolean dotSet1 = false;
    private boolean dotSet2 = false;
    private boolean buffer1Fixed = false;

    public Calculator(){

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMaximumSize(new Dimension(800, 1200));
        setResizable(true);
        setSize(400, 600);
        setTitle("Calculator");

        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        
        resultField = new JLabel("0");
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setFont(font);
        addComponentToContainer(resultField, container, 0, 0, 4, 1);

        numberButtons = new HashMap<>();
        operatorButtons = new HashMap<>();

        // Buttons of calculator initialization
        {
            seven = buttonInit("7");
            numberButtons.put(seven, "7");
            addComponentToContainer(seven, container, 0, 1, 1, 1);

            eight = buttonInit("8");
            numberButtons.put(eight, "8");
            addComponentToContainer(eight, container, 1, 1, 1, 1);

            nine = buttonInit("9");
            numberButtons.put(nine, "9");
            addComponentToContainer(nine, container, 2, 1, 1, 1);

            plus = buttonInit("+");
            operatorButtons.put(plus, "+");
            addComponentToContainer(plus, container, 3, 1, 1, 1);

            four = buttonInit("4");
            numberButtons.put(four, "4");
            addComponentToContainer(four, container, 0, 2, 1, 1);

            five = buttonInit("5");
            numberButtons.put(five, "5");
            addComponentToContainer(five, container, 1, 2, 1, 1);

            six = buttonInit("6");
            numberButtons.put(six, "6");
            addComponentToContainer(six, container, 2, 2, 1, 1);

            minus = buttonInit("-");
           operatorButtons.put(minus, "-");
            addComponentToContainer(minus, container, 3, 2, 1, 1);

            one = buttonInit("1");
            numberButtons.put(one, "1");
            addComponentToContainer(one, container, 0, 3, 1, 1);

            two = buttonInit("2");
            numberButtons.put(two, "2");
            addComponentToContainer(two, container, 1, 3, 1, 1);

            three = buttonInit("3");
            numberButtons.put(three, "3");
            addComponentToContainer(three, container, 2, 3, 1, 1);

            multiply = buttonInit("*");
            operatorButtons.put(multiply, "*");
            addComponentToContainer(multiply, container, 3, 3, 1, 1);

            clear = buttonInit("CE");
            clear.setMargin(new Insets(0, 0, 0, 0));
            addComponentToContainer(clear, container, 0, 4, 1, 1);

            zero = buttonInit("0");
            numberButtons.put(zero, "0");
            addComponentToContainer(zero, container, 1, 4, 1, 1);

            dot = buttonInit(".");
            numberButtons.put(dot, ".");
            addComponentToContainer(dot, container, 2, 4, 1, 1);

            div = buttonInit("/");
            operatorButtons.put(div, "/");
            addComponentToContainer(div, container, 3, 4, 1, 1);

            result = buttonInit("=");
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
        return button;
    }

    public void actionPerformed(ActionEvent event){

        JButton button = (JButton)event.getSource();
        String curString = "";

        if (button.equals(clear)){
            clearData();
            resultField.setText("0");
        }

        //Input of the first number
        if (buffer2.equals("")) {
            //No operation stored && input = numbers or dot
            if (!operatorSet && numberButtons.containsKey(button)) {
                curString = numberButtons.get(button);
                buffer1 = addNumbersBuffer1(curString);
                resultField.setText(buffer1);
            }
            if (!operatorSet && operatorButtons.containsKey(button)) {
                if (button.equals(result)) {
                    buffer1 = calculate(buffer1, "", "");
                    resultField.setText(buffer1);
                    operatorSet = true;
                } else {
                    operator = operatorButtons.get(button);
                    operatorSet = true;
                }
            } else if (operatorSet && operatorButtons.containsKey(button)) {
                if (button.equals(result)) {
                    buffer1 = calculate(buffer1, "", "");
                    resultField.setText(buffer1);
                } else {
                    operator = operatorButtons.get(button);
                }
            } else if (operatorSet && numberButtons.containsKey(button)){
                buffer2 = addNumbersBuffer2(button);
                resultField.setText(buffer2);
            }
        } else if (numberButtons.containsKey(button)){
            String result = addNumbersBuffer2(button);
            resultField.setText(result);
        } else if (operatorButtons.containsKey(button)){
            if (!button.equals(result)){
                buffer1 = calculate(buffer1 , operator, buffer2);
                resultField.setText(buffer1);
                buffer2 = "";
                dotSet1 = false;
                dotSet2 = false;
                operator = operatorButtons.get(button);
                operatorSet = true;
            } else {
                buffer1 = calculate(buffer1 , operator, buffer2);
                buffer2 = "";
                operator = operatorButtons.get(button);
                operatorSet = true;
                resultField.setText(buffer1);
                if (buffer1.equals("error")) {
                    buffer1 = "0";
                }
                dotSet1 = false;
                dotSet2 = false;
            }
        }
    }

    public String addNumbersBuffer2(JButton button){
        String curString = numberButtons.get(button);
        if (buffer2.equals("") && (!dotSet2)) {
            if (curString.equals(".")) {
                buffer2 = "0" + curString;
                dotSet2 = true;
            } else {
                buffer2 = curString;
            }
        } else if (!buffer2.equals("") && (!dotSet2)) {
            if (curString.equals(".")) {
                buffer2 += curString;
                dotSet2 = true;
            } else {
                buffer2 += curString;
            }
        } else if (!buffer2.equals("") && dotSet2) {
            if (!curString.equals(".")) {
                buffer2 += curString;
            }
        }
        return buffer2;
    }

    public String addNumbersBuffer1(String curStr){

        // If buffer1 is 0 without dot
        if (buffer1.equals("0") && (!dotSet1)) {
            // and input = dot => buffer1 = 0.
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
            } else {
                // if input != dot => buffer1 = number
                buffer1 = curStr;
            }
            // If buffer1 isn't 0 and no dot
        } else if (!buffer1.equals("0") && (!dotSet1)) {
            // if input = dot => buffer1 = numbers.
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
                // if input != dot => buffer1 = numbers
            } else {
                buffer1 += curStr;
            }
            // if buffer1 isn't 0 and there is a dot
        } else if (!buffer1.equals("0") && dotSet1) {
            // add number to buffer1 if input != dot
            if (!curStr.equals(".")) {
                buffer1 += curStr;
            }
        }
        return buffer1;
    }

    public String calculate(String buf1, String op, String buf2){

        double result = 0;
        String error = "error";

        double num1 = Double.parseDouble(buf1);
        double num2 = Double.parseDouble(buf2);

        switch (op){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1*num2;
                break;
            case "/":
                if (num2 == 0){
                    return error;
                } else {
                    result = num1 / num2;
                    break;
                }
            default:
                result = num1;
        }
        return String.valueOf(result);
    }

    public void clearData(){
        buffer1 = "0";
        buffer2 = "";
        operatorSet = false;
        operator = "";
        dotSet1 = false;
        dotSet2 = false;
    }

}
