/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class Calculator extends JFrame implements ActionListener, KeyListener{
    
    private JLabel resultField;
    private Font font = new Font("Arial", Font.BOLD, 20);

    private HashMap<JButton, String> numberButtons;
    private HashMap<JButton, String> operatorButtons;

    private String buffer1 = "0";
    private String buffer2 = "";
    private String operator = "";
    private boolean operatorSet = false;
    private boolean dotSet1 = false;
    private boolean dotSet2 = false;;
    private String curResult = "";

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

            JButton clear = buttonInit("CE");
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

    public void actionPerformed(ActionEvent event) {

        JButton button=(JButton) event.getSource();
        if (numberButtons.containsKey(button)){
            actionsByButtons(numberButtons.get(button));
        } else if (operatorButtons.containsKey(button)){
            actionsByButtons(operatorButtons.get(button));
        } else if (button.getText().equals("CE")){
            actionsByButtons("CE");
        }
    }

    private void actionsByButtons(String buttonValue){
        if (buttonValue.equals("CE")){
            clearData();
            resultField.setText(buffer1);
        }

        if (buffer2.equals("")) {
            if (!operatorSet && numberButtons.containsValue(buttonValue) && curResult.equals("")) {
                buffer1 = addNumbersBuffer1(buttonValue);
                resultField.setText(buffer1);
            } else if (!operatorSet && numberButtons.containsValue(buttonValue) && !curResult.equals("")){
                clearData();
                buffer1 = addNumbersBuffer1(buttonValue);
                resultField.setText(buffer1);
            }
            if (!operatorSet && operatorButtons.containsValue(buttonValue)) {
                if (buttonValue.equals("=")) {
                    curResult = calculate(buffer1, "", "");
                    copyCurResultToClipboard(curResult);
                    resultField.setText(curResult);
                    buffer1 = curResult;
                    operatorSet = true;
                    operator = "=";
                } else {
                    operator = buttonValue;
                    operatorSet = true;
                }
            } else if (operatorSet && operatorButtons.containsValue(buttonValue)) {
                if (buttonValue.equals("=")) {
                    curResult=calculate(buffer1, "", "");
                    copyCurResultToClipboard(curResult);
                    resultField.setText(curResult);
                    buffer1=curResult;
                    operator = "=";
                    operatorSet = true;
                } else {
                    operator = buttonValue;
                }
            } else if(operatorSet && numberButtons.containsValue(buttonValue) && !curResult.equals("")) {
                if (operator.equals("=")) {
                    clearData();
                    buffer1=addNumbersBuffer1(buttonValue);
                    resultField.setText(buffer1);
                } else {
                    buffer2 = addNumbersBuffer2(buttonValue);
                    resultField.setText(buffer2);
                }
            } else if (operatorSet && numberButtons.containsValue(buttonValue)){
                buffer2 = addNumbersBuffer2(buttonValue);
                resultField.setText(buffer2);
            }
        } else if (numberButtons.containsValue(buttonValue)){
            buffer2 = addNumbersBuffer2(buttonValue);
            resultField.setText(buffer2);
        } else if (operatorButtons.containsValue(buttonValue)){
            if (!buttonValue.equals("=")){
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                resultField.setText(curResult);
                buffer1 = curResult;
                operator = buttonValue;
                operatorSet = true;
            } else {
                curResult = calculate(buffer1 , operator, buffer2);
                copyCurResultToClipboard(curResult);
                resultField.setText(curResult);
                buffer1 = curResult;
                if (buffer1.equals("error")) {
                    clearData();
                }
            }
        }
    }

    private String addNumbersBuffer2(String curString){
        if (buffer2.equals("") && (!dotSet2)) {
            if (curString.equals(".")) {
                buffer2 = "0" + curString;
                dotSet2 = true;
            } else {
                buffer2 = curString;
            }
        } else if (!buffer2.equals("") && (!dotSet2)) {
            if (curString.equals("0") && buffer2.equals("0")){
                buffer2 = curString;
            } else if (curString.equals(".")) {
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


    private String addNumbersBuffer1(String curStr){

        if (buffer1.equals("0") && (!dotSet1)) {
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
            } else {
                buffer1 = curStr;
            }
        } else if (!buffer1.equals("0") && (!dotSet1)) {
            if (curStr.equals(".")) {
                buffer1 += curStr;
                dotSet1 = true;
            } else {
                buffer1 += curStr;
            }
        } else if (!buffer1.equals("0") && dotSet1) {
            if (!curStr.equals(".")) {
                buffer1 += curStr;
            }
        }
        return buffer1;
    }

    private String calculate(String buf1, String op, String buf2){

        double result;
        double num2;

        double num1 = Double.parseDouble(buf1);
        if (!buf2.equals("")) {
            num2=Double.parseDouble(buf2);
        } else {
            //have no sense
            //TODO: change
            num2 = 0;
        }

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
                    return "error";
                } else {
                    result = num1 / num2;
                    break;
                }
            default:
                result = num1;
        }

        clearData();
        return String.valueOf(result);
    }

    private void clearData(){
        buffer1 = "0";
        buffer2 = "";
        operatorSet = false;
        operator = "";
        dotSet1 = false;
        dotSet2 = false;
        curResult = "";
    }

    private void copyCurResultToClipboard(String curResult){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection selection = new StringSelection(curResult);
        clipboard.setContents(selection, selection);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String s = String.valueOf(e.getKeyChar());
        if (operatorButtons.containsValue(s)){
            actionsByButtons(s);
        } else if (numberButtons.containsValue(s)){
            actionsByButtons(s);
        } if (s.equals("\n")){
            actionsByButtons("=");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
