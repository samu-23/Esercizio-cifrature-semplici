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
import esercizio.cifrature.semplici.CrittografiaCesare;
import esercizio.cifrature.semplici.CrittografiaVigenere;

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
    JLabel keyLabel = new JLabel("Key: ");
    JTextField keyTextField = new JTextField();
    
    JLabel agentLabel = new JLabel("Agent Code: ");
    JTextField agentTextField = new JTextField();
    
    JLabel ipLabel = new JLabel("IP: ");
    JTextField ipTextField = new JTextField();
    JLabel portLabel = new JLabel("Port: ");
    JTextField portTextField = new JTextField();
    JButton confirmButton = new JButton("Insert");
    JButton sendButton = new JButton("Send");
    
    JRadioButton cesare = new JRadioButton("Cesare");
    JRadioButton vigenere = new JRadioButton("Vigenerè");
    
    JLabel titoloLabel = new JLabel("Secret Sender");
    
    public MenuSender(String title, MenuPrincipale menu) {
        setTitle(title);
        setBounds(200, 100, 1000, 400);
        
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
        keyTextField.setPreferredSize(new Dimension(50,20));
        agentTextField.setPreferredSize(new Dimension(50,20));
        
        ipPanel.add(ipLabel);
        ipPanel.add(ipTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(50,0)));
        ipPanel.add(portLabel);
        ipPanel.add(portTextField);
        ipPanel.add(Box.createRigidArea(new Dimension(20,0)));
        ipPanel.add(confirmButton);
        
        cesare.setBackground(Color.decode("#D1D1D1"));
        vigenere.setBackground(Color.decode("#D1D1D1"));
        
        cesare.addActionListener(this);
        vigenere.addActionListener(this);
        
        titoloLabel.setLocation(WIDTH/2, 0);
        titoloLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        northPanel.add(titoloLabel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        messagePanel.setBackground(Color.decode("#D1D1D1"));
        messagePanel.add(agentLabel);
        messagePanel.add(agentTextField);
        messagePanel.add(Box.createRigidArea(new Dimension(20, 50)));
        messagePanel.add(messageLabel);
        messagePanel.add(messageTextArea);
        messagePanel.add(Box.createRigidArea(new Dimension(100, 50)));
        messagePanel.add(keyLabel);
        messagePanel.add(keyTextField);
        messagePanel.add(Box.createRigidArea(new Dimension(40, 50)));
        messagePanel.add(cesare);
        messagePanel.add(vigenere);
        messagePanel.add(Box.createRigidArea(new Dimension(40, 50)));
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
                
                if (ipTextField.getText().isEmpty() || ipTextField.getText() == null) {
                    throw new Exception();
                }
                
                if (portTextField.getText().isEmpty() || portTextField.getText() == null) {
                    throw new Exception();
                }
                
                JOptionPane.showMessageDialog(null, "IP e porta inseriti con successo");
                ipTextField.setEditable(false);
                portTextField.setEditable(false);
                confirmButton.setEnabled(false);
                
            } catch (IllegalArgumentException argumentex) {
                JOptionPane.showMessageDialog(null, "Porta non valida! Inserisci una porta tra 1-65535");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Controllare i campi IP e Port.");
            }
            
        }
        
        if (e.getSource() == sendButton) {
            
            try {
                String ipString = ipTextField.getText();
                int portInt = Integer.parseInt(portTextField.getText());

                if (messageTextArea.getText().length() >= 512) {
                    throw new IllegalArgumentException();
                }
                
                if (agentTextField.getText().isEmpty() || agentTextField.getText() == null) throw new IndexOutOfBoundsException();
                String bindedString = agentTextField.getText() + ": " + messageTextArea.getText();
                
                if (cesare.isSelected() || vigenere.isSelected()) sendMessage(bindedString, ipString, portInt); 
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Controlla di aver inserito il tuo codice Agente.");
            } catch (IllegalArgumentException ilex) {
                JOptionPane.showMessageDialog(null, "Scrivi meno di 512 caratteri!\n" + ilex);
            }
        }
        
        if (e.getSource() == cesare) {
            
            if (vigenere.isSelected() && cesare.isSelected()) {
                vigenere.setSelected(false);
            }
            
        }
        
        if (e.getSource() == vigenere) {
            
            if (cesare.isSelected() && vigenere.isSelected()) {
                cesare.setSelected(false);
            }
            
        }
        
    }
    
    public void sendMessage(String message, String ip, int port) {
        
        if (cesare.isSelected()) {
            try{
               
                DatagramSocket socketUDP = new DatagramSocket();

                int key = Integer.parseInt(keyTextField.getText());
                
                String criptedMessage = CrittografiaCesare.crittaMessaggio(message, key);
                
                byte[] sendData = criptedMessage.getBytes();

                DatagramPacket dp = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port);

                socketUDP.send(dp);
                
                socketUDP.close();
                
            } catch (IOException ioex) {
                JOptionPane.showMessageDialog(null, "Error: " + ioex);
            } catch (IllegalArgumentException iaex) {
                JOptionPane.showMessageDialog(null, "Errore!\nAssicurati di aver inserito solo numeri nella chiave!");
            }
        } else if (vigenere.isSelected()) {
            
            try{
               
                DatagramSocket socketUDP = new DatagramSocket();

                String key = keyTextField.getText();
                
                for (int i = 0; i < key.length(); i++) {
                    if (Integer.valueOf(key.charAt(i)) < 65 || Integer.valueOf(key.charAt(i)) > 90) {
                        if (Integer.valueOf(key.charAt(i)) < 97 || Integer.valueOf(key.charAt(i)) > 122) throw new IllegalArgumentException();
                    }
                }
                
                String criptedMessage = CrittografiaVigenere.crittaMessaggio(messageTextArea.getText(), key);
                
                byte[] sendData = criptedMessage.getBytes();

                DatagramPacket dp = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port);

                socketUDP.send(dp);
                
                socketUDP.close();
                
            } catch (IOException ioex) {
                JOptionPane.showMessageDialog(null, "Error: " + ioex);
            } catch (IllegalArgumentException iaex) {
                JOptionPane.showMessageDialog(null, "Errore!\nAssicurati di aver inserito solo lettere nella chiave!");
            }
        }
        
    }
    
    
}
