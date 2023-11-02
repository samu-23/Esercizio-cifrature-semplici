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
        
        messagePanel.add(messageLabel);
        messagePanel.add(messageTextArea);
        
        centralPanel.add(ipPanel,BorderLayout.NORTH);
        centralPanel.add(messagePanel, BorderLayout.SOUTH);
        
        comp.add(northPanel, BorderLayout.NORTH);
        comp.add(Box.createRigidArea(new Dimension(0,20)));
        comp.add(centralPanel,BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            try {
                
                String ipString = ipTextField.getText();
                int portInt = Integer.parseInt(portTextField.getText());
                
                InetAddress ip = InetAddress.getByName(ipString);
                InetSocketAddress socketIP = new InetSocketAddress(ip, portInt);
                
                JOptionPane.showMessageDialog(null, "IP e porta inseriti con successo");
                ipTextField.setEditable(false);
                portTextField.setEditable(false);
                confirmButton.setEnabled(false);
                
                DatagramSocket socketUDP = new DatagramSocket(socketIP);
                
                
            } catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(null, "Riguarda i campi IP e Port!");
            } catch (SocketException socketex) {
                JOptionPane.showMessageDialog(null, "Socket failed to connect: " + socketex.getLocalizedMessage());
            }
            
        }
    }
    
    
}
