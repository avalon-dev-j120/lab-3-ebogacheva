/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JAVA
 */
public class NumberButton extends JFrame{
    
    private JButton button;
    private String info;
    private Font font = new Font("Arial", Font.BOLD, 24);

    public NumberButton(String info) {
        this.info = info;
        button = new JButton(info);
        button.setFont(font);
    }
    
    public JButton getButton(){
        return this.button;
    }
    
    public String getIngo(){
        return this.info;
    }
    
}
