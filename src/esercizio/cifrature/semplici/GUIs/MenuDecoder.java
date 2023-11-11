/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import esercizio.cifrature.semplici.CrittografiaCesare;
import esercizio.cifrature.semplici.CrittografiaVigenere;

/**
 *
 * @author acer
 */
public class MenuDecoder extends JFrame implements ActionListener {

    ArrayList<String> messagesInbox;
    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    JLabel titleLabel = new JLabel("Secret Decoder");
    
    JPanel centralPanel = new JPanel();
    
    JComboBox<String> messagesComboBox;
    JButton confirmButton = new JButton("Decode");
    JRadioButton cesareDec = new JRadioButton("Cesare");
    JRadioButton vigenereDec = new JRadioButton("Vigenerè");
    JLabel keyLabel = new JLabel("Key: ");
    JTextField keyTextField = new JTextField();
    
    public MenuDecoder(String title, ArrayList<String> messages) {
        
        messagesInbox = messages;
        
        setTitle(title);
        setBounds(100,100,400,600);
        setResizable(false);
        
        messagesComboBox = new JComboBox<>();
        messagesComboBox.setPreferredSize(new Dimension(100,20));
        for (int i = 0; i < messages.size(); i++) {
            messagesComboBox.addItem(i + " - " + messages.get(i));
        }
        
        titleLabel.setLocation(WIDTH/2, 0);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        
        keyTextField.setPreferredSize(new Dimension(100,20));
        
        cesareDec.addActionListener(this);
        vigenereDec.addActionListener(this);
        confirmButton.addActionListener(this);
        
        messagesInbox = messages;
        
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        northPanel.add(titleLabel, BorderLayout.NORTH);
        
        centralPanel.add(messagesComboBox);
        centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
        centralPanel.add(confirmButton);
        centralPanel.add(Box.createRigidArea(new Dimension(100,0)));
        centralPanel.add(cesareDec);
        centralPanel.add(vigenereDec);
        centralPanel.add(Box.createRigidArea(new Dimension(50,0)));
        centralPanel.add(keyLabel);
        centralPanel.add(keyTextField);
        
        comp.add(centralPanel, BorderLayout.CENTER);
        comp.add(northPanel, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == cesareDec) {
            
            if (vigenereDec.isSelected() && cesareDec.isSelected()) {
                vigenereDec.setSelected(false);
            }
            
        }
        
        if (e.getSource() == vigenereDec) {
            
            if (cesareDec.isSelected() && vigenereDec.isSelected()) {
                cesareDec.setSelected(false);
            }
            
        }
        
        if (e.getSource() == confirmButton) {
            
            if (cesareDec.isSelected()) {
                
               String toDecrypt = messagesComboBox.getSelectedItem().toString().substring(4,messagesComboBox.getSelectedItem().toString().length());
               String originalMessage = CrittografiaCesare.decrittaMessaggio(toDecrypt, Integer.parseInt(keyTextField.getText()));
               JOptionPane.showMessageDialog(null, "Decrypted: " + originalMessage);
                
            } else if (vigenereDec.isSelected()) {
                
               String toDecrypt = messagesComboBox.getSelectedItem().toString().substring(4,messagesComboBox.getSelectedItem().toString().length());
               String originalMessage = CrittografiaVigenere.decrittaMessaggio(toDecrypt, keyTextField.getText());
               JOptionPane.showMessageDialog(null, "Decrypted: " + originalMessage);
                
            }
            
        }
        
    }
    
    
    
    
}
