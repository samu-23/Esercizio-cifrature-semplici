/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio.cifrature.semplici;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author acer
 */
public class MenuSender extends JFrame implements ActionListener {

    private MenuPrincipale menu;
    
    public MenuSender(String title, MenuPrincipale menu) {
        setTitle(title);
        setBounds(200, 100, 800, 600);
        
        this.menu = menu;
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
            menu.setVisible(true);
        }
        });
    }
    
    
}
