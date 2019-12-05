/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color_selection;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author JAVA
 */
public class ColorSelectionForm extends JFrame{
    
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JLabel red;
    private JLabel green;
    private JLabel blue;
    private JPanel colorPanel;
    
    private JPanel labelPanel1;
    private JPanel sliderPanel;

    
    public ColorSelectionForm(){
        
        setSize(600,300);
        
        red = new JLabel("Red :");
        green = new JLabel("Green :");
        blue = new JLabel("Blue:");  
        
        colorPanel = new JPanel();
        colorPanel.setBackground(new Color(100, 150,75));

        slider1 = new JSlider();
        slider2 = new JSlider();
        slider3 = new JSlider();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        

        sliderPanel= new JPanel();
        sliderPanel.add(slider1);
        sliderPanel.add(slider2);
        sliderPanel.add(slider3);
        sliderPanel.setLayout(new GridLayout(3,1,30,5));
        
        
        Container container = getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 3;
//        constraints.ipadx = 150;
//        constraints.ipady = 50;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        container.add(colorPanel, constraints);
        
        labelPanel1 = new JPanel();
        labelPanel1.add(red);
        labelPanel1.add(green);
        labelPanel1.add(blue);
        labelPanel1.setLayout(new GridLayout(3,1,5,5));

        
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        container.add(labelPanel1, constraints);
        
        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        container.add(sliderPanel, constraints);

        setVisible(true);


    }
    

    
}
