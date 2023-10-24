/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Tommaso Melani
 */
public class MenuPrincipale extends JFrame implements ActionListener {

    // Inizializzazione Componenti
    Container comp = this.getContentPane();
    JPanel panel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    
    JLabel titoloLabel = new JLabel("Crittografia");
    JButton bottoneSender = new JButton("Sender");
    JButton bottoneReceiver = new JButton("Receiver");
    
    public MenuPrincipale(String title) {
        setTitle(title);
        setBounds(200, 100, 800, 600);
        
        // Componenti del JFrame
        
        titoloLabel.setLocation(WIDTH/2, 0);
        titoloLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        bottoneSender.setLocation(WIDTH/2-100, 100);
        bottoneReceiver.setLocation(WIDTH/2+100, 100);
        
        bottoneSender.addActionListener(this);
        bottoneReceiver.addActionListener(this);
        
        panel.add(titoloLabel, BorderLayout.NORTH);
        buttonsPanel.add(bottoneSender, BorderLayout.CENTER);
        buttonsPanel.add(bottoneReceiver);
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        panel.setBackground(Color.decode("#D1D1D1"));
        
        comp.add(panel, BorderLayout.NORTH);
        comp.add(buttonsPanel,BorderLayout.CENTER);
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == bottoneSender) {
           //JOptionPane.showMessageDialog(this, "Hai cliccato Sender", "Alert!", JOptionPane.WARNING_MESSAGE);
           new MenuSender("Secret Sender", this);
           this.setVisible(false);
       }
       
       if (e.getSource() == bottoneReceiver) {
           JOptionPane.showMessageDialog(this, "Hai cliccato Receiver", "Alert!", JOptionPane.WARNING_MESSAGE);
       }
    }
    
    
    
}
