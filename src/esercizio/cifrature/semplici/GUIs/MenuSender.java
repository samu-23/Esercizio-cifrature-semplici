/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author acer
 */
public class MenuSender extends JFrame implements ActionListener {

    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    JPanel ipPanel = new JPanel();
    JPanel messagePanel = new JPanel();
    JLabel messageLabel = new JLabel("Message: ");
    JTextArea messageTextArea = new JTextArea();
    
    JLabel ipLabel = new JLabel("IP: ");
    JTextField ipTextField = new JTextField();
    JLabel portLabel = new JLabel("Port: ");
    JTextField portTextField = new JTextField();
    JButton confirmButton = new JButton("Insert");
    JButton sendButton = new JButton("Send");
    
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
        
        confirmButton.addActionListener(this);
        sendButton.addActionListener(this);
        
        messageTextArea.setPreferredSize(new Dimension(100,100));
        messageTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
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
        
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        messagePanel.setBackground(Color.decode("#D1D1D1"));
        messagePanel.add(messageLabel);
        messagePanel.add(messageTextArea);
        messagePanel.add(sendButton);
        
        centralPanel.add(ipPanel,BorderLayout.NORTH);
        centralPanel.add(Box.createRigidArea(new Dimension(100, 50)));
        centralPanel.add(messagePanel, BorderLayout.SOUTH);
        
        comp.add(northPanel, BorderLayout.NORTH);
        comp.add(centralPanel,BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            try {
                
                
                
                JOptionPane.showMessageDialog(null, "IP e porta inseriti con successo");
                ipTextField.setEditable(false);
                portTextField.setEditable(false);
                confirmButton.setEnabled(false);
                
            } catch (IllegalArgumentException argumentex) {
                JOptionPane.showMessageDialog(null, "Porta non valida! Inserisci una porta tra 1-65535");
            }
            
        }
        
        if (e.getSource() == sendButton) {
            
            try{
                String ipString = ipTextField.getText();
                int portInt = Integer.parseInt(portTextField.getText());

                InetAddress ip = InetAddress.getByName(ipString);
                InetSocketAddress socketIP = new InetSocketAddress(ip, portInt);

                DatagramSocket socketUDP = new DatagramSocket(socketIP);

                byte[] sendData = messageTextArea.getText().getBytes();

                DatagramPacket dp = new DatagramPacket(sendData, sendData.length, socketIP);

                socketUDP.send(dp);
                
            } catch (IOException ioex) {
                JOptionPane.showMessageDialog(null, "Error: " + ioex);
            }
        }
    }
    
    
}
