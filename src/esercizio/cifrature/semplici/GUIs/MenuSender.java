/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author acer
 */
public class MenuSender extends JFrame implements ActionListener {

    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    
    JPanel ipPanel = new JPanel();
    JLabel ipLabel = new JLabel("IP: ");
    JTextField ipTextField = new JTextField();
    JLabel portLabel = new JLabel("Port: ");
    JTextField portTextField = new JTextField();
    JButton confirmButton = new JButton("Insert");
    
    JLabel titoloLabel = new JLabel("Secret Sender");
    
    public MenuSender(String title, MenuPrincipale menu) {
        setTitle(title);
        setBounds(200, 100, 800, 600);
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menu.setVisible(true);
            }
        });
        
        ipTextField.setPreferredSize(new Dimension(100, 20));
        portTextField.setPreferredSize(new Dimension(100, 20));
        
        ipPanel.add(ipLabel);
        ipPanel.add(ipTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(50,0)));
        ipPanel.add(portLabel);
        ipPanel.add(portTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(20,0)));
        ipPanel.add(confirmButton);
        
        titoloLabel.setLocation(WIDTH/2, 0);
        titoloLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        northPanel.add(titoloLabel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        
        centralPanel.add(ipPanel,BorderLayout.CENTER);
        
        comp.add(northPanel, BorderLayout.NORTH);
        comp.add(centralPanel,BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
