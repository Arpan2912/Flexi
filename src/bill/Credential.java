/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import static bill.Bill.img;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Thanx
 */
public class Credential {
    String password="123";
    JFrame f_credential;
    JTextField t_credential;
    JButton b_credential;
    public Credential() {
        f_credential=new JFrame("Flexi Billing Solution");
        f_credential.setSize(1000, 1500);
        f_credential.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_credential.getContentPane().setBackground(Color.DARK_GRAY);
        f_credential.setIconImage(img);
        
        
        JLabel ltitle=new JLabel("Flexi Billing Solution");
        ltitle.setBounds(250,200,500, 100);
        ltitle.setForeground(Color.white);
        ltitle.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 50));
        
        t_credential=new JTextField();
        t_credential.setBounds(380, 300,120,30);
       t_credential.setBackground(Color.white);
       t_credential.setForeground(Color.DARK_GRAY);
        
        b_credential=new JButton("Login");
        b_credential.setBounds(400,350, 70, 30);
        b_credential.setBackground(Color.white);
        b_credential.setForeground(Color.gray);
        
        f_credential.add(ltitle);
        f_credential.add(t_credential);
        f_credential.add(b_credential);
        f_credential.setLayout(null);
        f_credential.setVisible(true);
        
        b_credential.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t_credential.getText().equalsIgnoreCase(password))
                {
                    f_credential.setVisible(false);
                    new Bill().homeFrame();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }
    
            
}
