/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import static bill.Bill.con;
import static bill.Bill.frame_width;
import static bill.Bill.img;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Thanx
 */
public class AddItem {

    JFrame f_add_Item;
    JLabel l_item_name;
    JLabel l_item_price;
    JLabel l_item_gst;
    JLabel l_item_stock;
    JLabel l_item_discount;
    JTextField t_item_name;
    JTextField t_item_price;
    JTextField t_item_gst;
    JTextField t_item_stock;
    JTextField t_item_discount;
    JButton b_add_item;
    Bill b = new Bill();
    int x = (Bill.frame_width / 2) - 190;
    int y = (Bill.frame_height / 2) - 180;
    int height = 30;
    int width = 70;
    int width1 = 200;
    int x1 = (Bill.frame_width / 2) - 100;
    Connection con;
    static Pattern pattern;
    static Matcher matcher;
    private static final String Validate_Pattern = "([0-9])$";
    public AddItem() {
        try {
            Class.forName(Db.driver_name);
            con = DriverManager.getConnection(Db.con_string, Db.user_name, Db.password);
            f_add_Item = new JFrame("Flexi Billing Solution");
            f_add_Item.setIconImage(img);
            f_add_Item.setSize(Bill.frame_width, Bill.frame_height);
            f_add_Item.getContentPane().setBackground(Color.WHITE);

            JLabel ltitle = new JLabel("Add Item");
            ltitle.setBounds(x + 50, y, 300, 80);

            ltitle.setForeground(Color.red);
            ltitle.setFont(new Font("verdana", Font.BOLD, 40));

            y = y + 80 + 10;
            l_item_name = new JLabel("Item Name");
            l_item_name.setBounds(x, y, width, height);
            l_item_name.setForeground(Color.red);

            x1 = x1 + width;
            t_item_name = new JTextField();
            t_item_name.setBounds(x1, y, width1, height);

            y = y + height + 5;
            l_item_price = new JLabel("MRP");
            l_item_price.setBounds(x, y, width, height);
            l_item_price.setForeground(Color.red);

            t_item_price = new JTextField();
            t_item_price.setBounds(x1, y, width1, height);

            y = y + height + 5;
            l_item_gst = new JLabel("GST Rate: ");
            l_item_gst.setBounds(x, y, width, height);
            l_item_gst.setForeground(Color.red);

            t_item_gst = new JTextField();
            t_item_gst.setBounds(x1, y, width1, height);

            y = y + height + 5;
            l_item_stock = new JLabel("STOCK ");
            l_item_stock.setBounds(x, y, width, height);
            l_item_stock.setForeground(Color.red);

            t_item_stock = new JTextField();
            t_item_stock.setBounds(x1, y, width1, height);

            y = y + height + 20;
            b_add_item = new JButton("Add Item");
            b_add_item.setBounds(x + 70, y, width1, height);
            b_add_item.setBackground(Color.red);
            b_add_item.setForeground(Color.white);

            f_add_Item.add(l_item_name);
            f_add_Item.add(t_item_name);
            f_add_Item.add(l_item_price);
            f_add_Item.add(t_item_price);
            f_add_Item.add(l_item_gst);
            f_add_Item.add(t_item_gst);
            f_add_Item.add(l_item_stock);
            f_add_Item.add(t_item_stock);

//        f_add_Item.add(l_item_discount);
//f_add_Item.add(t_item_discount);
            f_add_Item.add(b_add_item);
            f_add_Item.add(ltitle);
            f_add_Item.setLayout(null);
            f_add_Item.setVisible(true);
// b.f_home.setVisible(false);

            b_add_item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItem();
                }
            });
            b_add_item.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                        addItem();
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (ClassNotFoundException ex) {
            try {
                JOptionPane.showMessageDialog(f_add_Item, "please start server");
                ex.printStackTrace(new PrintStream(new File("log.txt")));
                // Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(f_add_Item, "please check server");
                ex.printStackTrace(new PrintStream(new File("log.txt")));
                //Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }
    public boolean validate(final String data) {
        pattern = Pattern.compile(Validate_Pattern);
        matcher = pattern.matcher(data);
        return matcher.matches();

    }
    public void addItem() {
        try {
            if (t_item_price.getText().equals("")) {
                t_item_price.setText("0");
            }
            else
            {
                boolean b=validate(t_item_price.getText());
                System.out.println(b);
                if(b==false)
                {
                    JOptionPane.showMessageDialog(f_add_Item, "please enter valid MRP");
                }
            }
            if (t_item_name.getText().equals("")) {
                JOptionPane.showMessageDialog(f_add_Item, "please add name");
            } else if (t_item_gst.getText().equals("")) {
                JOptionPane.showMessageDialog(f_add_Item, "please add gst");
            } else {
                String query = "insert into items(`Iname`,`Iprice`,`Igst`,`stock`) values (?,?,?,?)";
                PreparedStatement p = con.prepareStatement(query);
                p.setString(1, t_item_name.getText());
                p.setInt(2, Integer.parseInt(t_item_price.getText()));
                p.setFloat(3, Float.parseFloat(t_item_gst.getText()));
                p.setInt(4, Integer.parseInt(t_item_stock.getText()));

                //p.executeUpdate();
                JOptionPane.showMessageDialog(f_add_Item, "Record Inserted succesfully");
                t_item_name.setText("");
                t_item_price.setText("");
                t_item_gst.setText("");
                t_item_stock.setText("");

                try {
                    //b.fileCombo();
                } catch (Exception ex) {
                }

            }//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
