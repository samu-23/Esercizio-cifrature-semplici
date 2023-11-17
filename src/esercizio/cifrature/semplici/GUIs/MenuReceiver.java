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
import javax.swing.event.*;
import java.util.ArrayList;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class MenuReceiver extends JFrame implements ActionListener {
    
    private String[] infoTabella = {"Messaggio cifrato"};

    private ArrayList<String> messageInbox = new ArrayList<>();
    
    Container comp = this.getContentPane();
    JPanel northPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    
    JLabel titoloLabel = new JLabel("Secret Inbox");
    
    DefaultTableModel tableModel = new DefaultTableModel(infoTabella, 0) {
    
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    
    };
    
    JTable table = new JTable(tableModel);
    TableColumn firstColumn = table.getColumnModel().getColumn(0);
    
    
    
    JLabel getIpLabel = new JLabel("Your IP: ");
    JTextField getIpTextField = new JTextField();
    JButton getIpButton = new JButton("Get IP");
    
    JButton openDecoderButton = new JButton("Open Decoder");
    
    JLabel portLabel = new JLabel("Port: ");
    JTextField portTextField = new JTextField();
    JButton openSocketButton = new JButton("Open connections");
    
    public MenuReceiver(String title, MenuPrincipale menu) {
        
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
        
        titoloLabel.setLocation(WIDTH/2, 0);
        titoloLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        portTextField.setPreferredSize(new Dimension(100, 20));
        getIpTextField.setPreferredSize(new Dimension(100, 20));
        getIpTextField.setEditable(false);
        
        northPanel.add(titoloLabel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#D1D1D1"), 5, true));
        northPanel.setBackground(Color.decode("#D1D1D1"));
        
        openSocketButton.addActionListener(this);
        getIpButton.addActionListener(this);
        openDecoderButton.addActionListener(this);
        
        firstColumn.setPreferredWidth(400);
        addRow("Messaggio cifrato");
        
        // Disabilitare la selezione delle righe della tabella
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                table.getSelectionModel().clearSelection();
            }
        });
        
        centralPanel.add(portLabel);
        centralPanel.add(portTextField);
        centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
        centralPanel.add(getIpLabel);
        centralPanel.add(getIpTextField);
        centralPanel.add(getIpButton);
        centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
        centralPanel.add(openSocketButton);
        centralPanel.add(Box.createRigidArea(new Dimension(30,0)));
        centralPanel.add(openDecoderButton);
        centralPanel.add(Box.createRigidArea(new Dimension(40, 50)));
        centralPanel.add(table);
        
        comp.add(northPanel, BorderLayout.NORTH);
        comp.add(Box.createRigidArea(new Dimension(0,20)));
        comp.add(centralPanel, BorderLayout.CENTER);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openSocketButton) {
            
            
            
            try {
                
                while (true) {
                    
                    receiveMessages(Integer.parseInt(portTextField.getText()));
                    
                    Object[] options = {"Sì", "No"};
                    int choice = JOptionPane.showOptionDialog(null,"Vuoi ancora ricevere messaggi?","Secret Receiver", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    
                    if (choice == 1) {
                        break;
                    }
                }
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Errore!\nControlla di aver inserito la porta!");
            }
            
        }
        
        if (e.getSource() == getIpButton) {
            
            InetAddress thisIP;
            try {
                thisIP = InetAddress.getLocalHost();
                getIpTextField.setText(thisIP.getHostAddress());
            } catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        if (e.getSource() == openDecoderButton) {
            
            new MenuDecoder("Secret Decoder", messageInbox);
            
        }
        
    }
    
    public void receiveMessages(int port) {
        
        try {
            
            DatagramSocket socketUDP = new DatagramSocket(port);
            
            byte[] buffer = new byte[1024];
            
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            
            socketUDP.receive(dp);
            
            String message = new String(dp.getData(), 0, dp.getLength());
            
            messageInbox.add(message);
            
            addRow(message);
            
            socketUDP.close();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getLocalizedMessage());
        }
        
    }
    
    public void addRow(String nomeRiga) {
        
        Object[] row = {nomeRiga};
        tableModel.addRow(row);
        
    }
    
}
