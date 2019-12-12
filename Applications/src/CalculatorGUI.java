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


public class CalculatorGUI extends JFrame implements ActionListener{
    
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

    private HashMap<JButton, String> buttons;

    private String buffer = "0";
    private boolean action = false;


    public CalculatorGUI(){


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

        buttons = new HashMap<>();

        // Buttons of calculator initialization
        {
            seven = buttonInit("7");
            buttons.put(seven, "7");
            addComponentToContainer(seven, container, 0, 1, 1, 1);


            eight = buttonInit("8");
            buttons.put(eight, "8");
            addComponentToContainer(eight, container, 1, 1, 1, 1);

            nine = buttonInit("9");
            buttons.put(nine, "9");
            addComponentToContainer(nine, container, 2, 1, 1, 1);

            plus = buttonInit("+");
            buttons.put(plus, "+");
            addComponentToContainer(plus, container, 3, 1, 1, 1);

            four = buttonInit("4");
            buttons.put(four, "4");
            addComponentToContainer(four, container, 0, 2, 1, 1);

            five = buttonInit("5");
            buttons.put(five, "5");
            addComponentToContainer(five, container, 1, 2, 1, 1);

            six = buttonInit("6");
            buttons.put(six, "6");
            addComponentToContainer(six, container, 2, 2, 1, 1);

            minus = buttonInit("-");
            buttons.put(minus, "-");
            addComponentToContainer(minus, container, 3, 2, 1, 1);

            one = buttonInit("1");
            buttons.put(one, "1");
            addComponentToContainer(one, container, 0, 3, 1, 1);

            two = buttonInit("2");
            buttons.put(two, "2");
            addComponentToContainer(two, container, 1, 3, 1, 1);

            three = buttonInit("3");
            buttons.put(three, "3");
            addComponentToContainer(three, container, 2, 3, 1, 1);

            multiply = buttonInit("*");
            buttons.put(multiply, "*");
            addComponentToContainer(multiply, container, 3, 3, 1, 1);

            clear = buttonInit("CE");
            buttons.put(clear, "CE");
            clear.setMargin(new Insets(0, 0, 0, 0));
            addComponentToContainer(clear, container, 0, 4, 1, 1);

            zero = buttonInit("0");
            buttons.put(zero, "0");
            addComponentToContainer(zero, container, 1, 4, 1, 1);

            dot = buttonInit(".");
            buttons.put(dot, ".");
            addComponentToContainer(dot, container, 2, 4, 1, 1);

            div = buttonInit("/");
            buttons.put(div, "/");
            addComponentToContainer(div, container, 3, 4, 1, 1);

            result = buttonInit("=");
            buttons.put(result, "=");
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
        String curString = buttons.get(button);
        String curResult;
        Calculator calculator = new Calculator();

        if (button.equals(clear)){
            buffer = "0";
            resultField.setText("0");
        } else if (button.equals(result)){
            curResult = calculator.evaluate(buffer);
            resultField.setText(curResult);
            buffer = curResult;
        } else {
            if (buffer.equals("0")){
                buffer = curString;
            } else {
                buffer += curString;
            }
            resultField.setText(buffer);
        }
    }


}
