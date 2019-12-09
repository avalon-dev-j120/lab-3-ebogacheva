/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author JAVA
 */
public class Calculator extends JFrame{
    
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
    private Font font = new Font("Arial", Font.BOLD, 48);
    
    
    public Calculator(){
          
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(400, 600);
        setTitle("Calculator");
        
        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        
        resultField = new JLabel();
        resultField.setFont(font);
        addButtonToContainer(resultField, container, 0, 0, 4, 1);
       
        seven = buttonInit("7");
        addButtonToContainer(seven, container, 0, 1, 1, 1);
        
        eight = buttonInit("8");
        addButtonToContainer(eight, container, 1, 1, 1, 1);
               
        nine = buttonInit("9");
        addButtonToContainer(nine, container, 2, 1, 1, 1);
        
        plus = buttonInit("+");
        addButtonToContainer(plus, container, 3, 1, 1, 1);
        
        four = buttonInit("4");
        addButtonToContainer(four, container, 0, 2, 1, 1);
        
        five = buttonInit("5");
        addButtonToContainer(five, container, 1, 2, 1, 1);
        
        six = buttonInit("6");
        addButtonToContainer(six, container, 2, 2, 1, 1);
        
        minus = buttonInit("-");
        addButtonToContainer(minus, container, 3, 2, 1, 1);
        
        one = buttonInit("1");
        addButtonToContainer(one, container, 0, 3, 1, 1);
        
        two = buttonInit("2");
        addButtonToContainer(two, container, 1, 3, 1, 1);
        
        three = buttonInit("3");
        addButtonToContainer(three, container, 2, 3, 1, 1);
        
        multiply = buttonInit("*");
        addButtonToContainer(multiply, container, 3, 3, 1, 1);
        
        clear = buttonInit("CE");
        clear.setMargin(new Insets(0,0,0,0));
        addButtonToContainer(clear, container, 0, 4, 1, 1);
        
        zero = buttonInit("0");
        addButtonToContainer(zero, container, 1, 4, 1, 1);
        
        dot = buttonInit(".");
        addButtonToContainer(dot, container, 2, 4, 1, 1);
        
        div = buttonInit("/");
        addButtonToContainer(div, container, 3, 4, 1, 1);
        
        result = buttonInit("=");
        addButtonToContainer(result, container, 0, 5, 8, 1);
        
        setVisible(true);

    }
    
    private JButton buttonInit(String info){
        
        NumberButton button = new NumberButton(info);
        return button.getButton();
    }
    
    private void addButtonToContainer(JComponent component, Container container, int x,int y, int dx, int dy){
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
    
}
