/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class MenuDecoder extends JFrame implements ActionListener {

    ArrayList<String> messagesInbox;
    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    
    
    public MenuDecoder(String title, ArrayList<String> messages) {
        
        setTitle(title);
        setBounds(100,100,200,300);
        
        messagesInbox = messages;
        
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        
        comp.add(northPanel, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
    
}