/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 *
 * @author acer
 */
public class MenuReceiver extends JFrame implements ActionListener {
    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    
    JLabel titoloLabel = new JLabel("Secret Receiver");
    
    
    JLabel portLabel = new JLabel("Port: ");
    JTextField portTextField = new JTextField();
    JButton openSocketButton = new JButton("Open connections");
    
    public MenuReceiver(String title, MenuPrincipale menu) {
        
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
        
        titoloLabel.setLocation(WIDTH/2, 0);
        titoloLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        portTextField.setPreferredSize(new Dimension(100, 20));
        
        northPanel.add(titoloLabel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        
        openSocketButton.addActionListener(this);
        
        centralPanel.add(portLabel);
        centralPanel.add(portTextField);
        centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
        centralPanel.add(openSocketButton);
        
        comp.add(northPanel, BorderLayout.NORTH);
        comp.add(Box.createRigidArea(new Dimension(0,20)));
        comp.add(centralPanel, BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openSocketButton) {
            
            try {
                
                int port = Integer.parseInt(portTextField.getText());
                
                DatagramSocket socketReceiver = new DatagramSocket(port);
                
                JOptionPane.showMessageDialog(null, "In attesa di connessioni...");
                
                if (socketReceiver.isConnected()) {
                    
                }
                
                
            } catch (SocketException ex) {
                JOptionPane.showMessageDialog(null, "Socket failed to connect: " + ex.getLocalizedMessage());
            } catch (NumberFormatException nex) {
                JOptionPane.showMessageDialog(null, "Controlla che la porta sia solo composta da numeri!");
                portTextField.setText("");
            }
            
        }
    }
    
}
