/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import static bill.Bill.con;
import static bill.Bill.img;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Thanx
 */
public class GenerateBill {

    static float sgst_summary;
    static float cgst_summary;
    int x = 70;
    int y = 70;
    int height = 25;
    int width = 100;
    int x1 = 70;
    int y1 = 70;
    int height1 = 25;
    int width1 = 150;
    int width2 = 70;
    int width3 = 50;
    String invoice_type;
    float price[] = new float[30];
    float cgst[] = new float[30];
    float sgst[] = new float[30];
    float gst[] = new float[30];
    float qty[]=new float[30];
    float discount[] = new float[30];
    float base_price[] = new float[30];
    float grand_total;
    /// form 1 elements
    JButton bprint;
    JLabel lname;
    JTextField tname;
    JLabel laddress1;
    JLabel lgst_no;
    JTextField taddress1;
    JLabel lphone;
    JTextField tphone;
    JTextField tgst_no;
    JLabel litem[] = new JLabel[30];
    JComboBox<String> c_item[] = new JComboBox[30];
    JLabel lqty[] = new JLabel[30];
    JTextField tqty[] = new JTextField[30];
    JLabel ltotal[] = new JLabel[30];
    JTextField ttotal[] = new JTextField[30];
    JLabel ldiscount[] = new JLabel[10];
    JTextField tdiscount[] = new JTextField[30];
    JLabel lprice[] = new JLabel[30];
    JTextField tprice[] = new JTextField[30];
    JButton b_add_row[] = new JButton[1];
    JButton b_gross_total;

    JFrame frame = new JFrame("Flexi Billing Solution");

    Connection con;

    public void initialization() {
        try {
            Bill.count=0;
            //JFrame frame = new JFrame("Billing");
            Class.forName(Db.driver_name);
            con = DriverManager.getConnection(Db.con_string, Db.user_name, Db.password);
            frame.setSize(1050, 700);
            frame.getContentPane().setBackground(Color.GRAY);
            frame.setIconImage(img);

            JLabel ltitle = new JLabel("Generate INVOICE");
            ltitle.setBounds(x, y, 300, 30);
            ltitle.setForeground(Color.white);
            ltitle.setFont(new Font("verdana", Font.BOLD, 20));

            y = y + 30 + 10;
            bprint = new JButton("print");
            bprint.setBounds(x, y, width, height);
            bprint.setBackground(Color.white);
            bprint.setForeground(Color.gray);

            y = y + height + 5;
            lname = new JLabel("Name :");
            lname.setBounds(x, y, width, height);
            lname.setForeground(Color.white);

            tname = new JTextField();
            x1 = x1 + width + 5;
            tname.setBounds(x1, y, width1, height);

            y = y + height + 5;
            laddress1 = new JLabel("Address Line1:");
            laddress1.setBounds(x, y, width, height);
            laddress1.setForeground(Color.WHITE);
            taddress1 = new JTextField();
            taddress1.setBounds(x1, y, width1, height);

            y = y + height + 5;
            lphone = new JLabel("Phone No.:");
            lphone.setBounds(x, y, width, height);
            lphone.setForeground(Color.white);

            tphone = new JTextField();
            tphone.setBounds(x1, y, width1, height);
            
            y = y + height + 5;
            lgst_no = new JLabel("GSTIN NO.:");
            lgst_no.setBounds(x, y, width, height);
            lgst_no.setForeground(Color.white);

            tgst_no = new JTextField();
            tgst_no.setBounds(x1, y, width1, height);

            y = y + height + 5;
            litem[0] = new JLabel("Item :");
            litem[0].setBounds(x, y, width2, height);
            litem[0].setForeground(Color.white);

            c_item[0] = new JComboBox<>();
            c_item[0].setBounds(x1, y, width1, height);
            c_item[0].setBackground(Color.white);

            int x2 = x1 + width1 + 20;
            lqty[0] = new JLabel("Quantity :");
            lqty[0].setBounds(x2, y, width2, height);
            lqty[0].setForeground(Color.white);

            int x2_1 = x2 + width2;
            tqty[0] = new JTextField();
            tqty[0].setBounds(x2_1, y, width3, height);

            int x3 = x2_1 + width2 + 5;
            ltotal[0] = new JLabel("Total : ");
            ltotal[0].setBounds(x3, y, width3, height);
            ltotal[0].setForeground(Color.white);

            int x3_1 = x3 + width3;
            ttotal[0] = new JTextField();
            ttotal[0].setBounds(x3_1, y, width2, height);

            int x4 = x3_1 + width2 + 20;
            ldiscount[0] = new JLabel("Discount : ");
            ldiscount[0].setBounds(x4, y, width2, height);
            ldiscount[0].setForeground(Color.white);

            int x4_1 = x4 + width2 + 5;
            tdiscount[0] = new JTextField();
            tdiscount[0].setBounds(x4_1, y, width2, height);

            int x5 = x4_1 + width2 + 20;
            lprice[0] = new JLabel("MRP: ");
            lprice[0].setBounds(x5, y, width3, height);
            lprice[0].setForeground(Color.white);

            int x5_1 = x5 + width3;
            tprice[0] = new JTextField();
            tprice[0].setBounds(x5_1, y, width2, height);

            int x6 = x5_1 + width2 + 5;
            b_add_row[0] = new JButton("+");
            b_add_row[0].setBounds(x6, y, width2, height);
            b_add_row[0].setBackground(Color.WHITE);
            b_add_row[0].setForeground(Color.GRAY);

            // lqty[0]=new JLabel("Quantity");
            y = y + height + 5;
            b_gross_total = new JButton("0");
            b_gross_total.setBounds(x, y, width, height);

            frame.add(ltitle);
            frame.add(bprint);
            frame.add(tname);
            frame.add(lname);
            frame.add(laddress1);
            frame.add(taddress1);
            frame.add(lphone);
            frame.add(tphone);
            frame.add(lgst_no);
            frame.add(tgst_no);
            frame.add(litem[0]);
            frame.add(c_item[0]);
            frame.add(lqty[0]);
            frame.add(tqty[0]);
            frame.add(ltotal[0]);
            frame.add(ttotal[0]);
            frame.add(ldiscount[0]);
            frame.add(tdiscount[0]);
            frame.add(lprice[0]);
            frame.add(tprice[0]);
            frame.add(b_add_row[0]);

            frame.add(b_gross_total);
            frame.setLayout(null);
            frame.setVisible(true);
            fileCombo(0);
            bprint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Float.parseFloat(b_gross_total.getText())>500)
                    {
                        invoice_type="Tax";
                        new PrintPage().printPage(invoice_type,frame, taddress1, tphone,tgst_no, tname, c_item, tqty, base_price, tprice, tdiscount, cgst, sgst, ttotal, b_gross_total);
                    }
                    else
                    {
                        invoice_type="";
                        new PrintPage().printPage(invoice_type,frame, taddress1, tphone,tgst_no, tname, c_item, tqty, base_price, tprice, tdiscount, cgst, sgst, ttotal, b_gross_total);
                    }
                    //printComponent();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            bprint.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                       if(Float.parseFloat(b_gross_total.getText())>500)
                    {
                        invoice_type="Tax";
                        
                        new PrintPage().printPage(invoice_type,frame, taddress1, tphone,tgst_no, tname, c_item, tqty, base_price, tprice, tdiscount, cgst, sgst, ttotal, b_gross_total);
                    }
                    else
                    {
                        invoice_type="";
                        new PrintPage().printPage(invoice_type,frame, taddress1, tphone,tgst_no, tname, c_item, tqty, base_price, tprice, tdiscount, cgst, sgst, ttotal, b_gross_total);
                    }}
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_add_row[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /* if (tqty[Bill.count].getText().equals("")) {
                        tqty[Bill.count].setText(0 + "");
                    }*/
 /* if (ttotal[Bill.count].getText().equals("")) {
                        ttotal[Bill.count].setText(0 + "");
                    }*/
                    if (tqty[Bill.count].getText().equals("") || tqty[Bill.count].getText().equals("0")) {
                        JOptionPane.showMessageDialog(frame, "please enter quantity");
                    } else if ((ttotal[Bill.count].getText().equals("") || ttotal[Bill.count].getText().equals("0")) && (tdiscount[Bill.count].getText().equals("") || tdiscount[Bill.count].getText().equals("0"))) {
                        JOptionPane.showMessageDialog(frame, "please enter Total or discount");
                    } else {
                        System.out.println("value is : " + ttotal[Bill.count].getText());
                        frame.remove(b_add_row[0]);
                        frame.remove(b_gross_total);
                        y = y - height - 5;
                        Bill.count++;
                        addElement(Bill.count);
                    }
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_add_row[0].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                        if (tqty[Bill.count].getText().equals("")||tqty[Bill.count].getText().equals("0")) {
                            JOptionPane.showMessageDialog(frame, "please enter quantity");
                        } else if (ttotal[Bill.count].getText().equals("") && tdiscount[Bill.count].getText().equals("")) {
                            JOptionPane.showMessageDialog(frame, "please enter Total or discount");
                        } else {
                            frame.remove(b_add_row[0]);
                            frame.remove(b_gross_total);
                            y = y - height - 5;
                            Bill.count++;
                            addElement(Bill.count);
                        }
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            ttotal[0].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (ttotal[0].getText().equals("")) {
                        ttotal[0].setText(0 + "");
                    } else if (ttotal[0].getText().equals("0")) {

                    } else {
                        countDetail(0);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            c_item[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected_item = c_item[0].getSelectedItem().toString();
                    try {

                        PreparedStatement p = con.prepareStatement("select Iprice,Igst from items where Iname=?");
                        p.setString(1, selected_item);
                        ResultSet r = p.executeQuery();
                        while (r.next()) {
                            price[0] = r.getInt("Iprice");
                            gst[0] = r.getFloat("Igst");
                            
                            
                        }
                        tprice[0].setText(price[0] + "");
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (Exception ex) {

                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            tqty[0].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    String selected_item = c_item[0].getSelectedItem().toString();
                    try {

                        PreparedStatement p = con.prepareStatement("select Iprice,Igst from items where Iname=?");
                        p.setString(1, selected_item);
                        ResultSet r = p.executeQuery();
                        while (r.next()) {
                            price[0] = r.getInt("Iprice");
                            gst[0] = r.getFloat("Igst");
                            
                        }
                        tprice[0].setText(price[0] + "");
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (Exception ex) {

                    }

                }
            });
            tdiscount[0].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tdiscount[0].getText().equals("")) {
                        tdiscount[0].setText(0 + "");
                    } else {
                        countDetail(0);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    Bill.count=-1;
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowClosed(WindowEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowActivated(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "please start server");
            // Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "please start server");
            //Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addElement(int count) {
        y = y + height + 5;
        litem[count] = new JLabel("Item :");
        litem[count].setBounds(x, y, width2, height);
        litem[count].setForeground(Color.white);

        c_item[count] = new JComboBox<>();
        c_item[count].setBounds(x1, y, width1, height);

        int x2 = x1 + width1 + 20;
        lqty[count] = new JLabel("Quantity");
        lqty[count].setBounds(x2, y, width2, height);
        lqty[count].setForeground(Color.white);

        int x2_1 = x2 + width2;
        tqty[count] = new JTextField();
        tqty[count].setBounds(x2_1, y, width3, height);

        int x3 = x2_1 + width2 + 5;
        ltotal[count] = new JLabel("Total");
        ltotal[count].setBounds(x3, y, width3, height);
        ltotal[count].setForeground(Color.white);

        int x3_1 = x3 + width3;
        ttotal[count] = new JTextField();
        ttotal[count].setBounds(x3_1, y, width2, height);

        int x4 = x3_1 + width2 + 20;
        ldiscount[count] = new JLabel("Discount : ");
        ldiscount[count].setBounds(x4, y, width2, height);
        ldiscount[count].setForeground(Color.white);

        int x4_1 = x4 + width2 + 5;
        tdiscount[count] = new JTextField();
        tdiscount[count].setBounds(x4_1, y, width2, height);

        int x5 = x4_1 + width2 + 20;
        lprice[count] = new JLabel("MRP: ");
        lprice[count].setBounds(x5, y, width3, height);
        lprice[count].setForeground(Color.white);

        int x5_1 = x5 + width3;
        tprice[count] = new JTextField();
        tprice[count].setBounds(x5_1, y, width2, height);

        int x6 = x5_1 + width + 5;
        b_add_row[0] = new JButton("+");
        b_add_row[0].setBounds(x6, y, width2, height);
        b_add_row[0].setBackground(Color.white);
        b_add_row[0].setForeground(Color.gray);

        /* int x4 = x3_1 + width + 5;
        b_add_row[0] = new JButton("+");
        b_add_row[0].setBounds(x4, y, width, height);*/
        y = y + height + 5;
        b_gross_total = new JButton(grand_total + "");
        b_gross_total.setBounds(x, y, width, height);

        frame.add(litem[count]);
        frame.add(c_item[count]);
        frame.add(lqty[count]);
        frame.add(tqty[count]);
        frame.add(ltotal[count]);
        frame.add(ttotal[count]);
        frame.add(ldiscount[count]);
        frame.add(tdiscount[count]);
        frame.add(lprice[count]);
        frame.add(tprice[count]);
        frame.add(b_add_row[0]);
        frame.add(b_gross_total);

        frame.revalidate();
        frame.repaint();

        fileCombo(count);
        b_add_row[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (tdiscount[Bill.count].getText().equals("")) {
                    tdiscount[Bill.count].setText(0 + "");
                }
                if (ttotal[Bill.count].getText().equals("")) {
                    ttotal[Bill.count].setText(0 + "");
                }*/
                try{
                if (tqty[Bill.count].getText().equals("")||tqty[Bill.count].getText().equals("0")) {
                    JOptionPane.showMessageDialog(frame, "please enter quantity");
                } else if (ttotal[Bill.count].getText().equals("") && tdiscount[Bill.count].getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "please enter Total or discount");
                } else {
                    frame.remove(b_add_row[0]);
                    frame.remove(b_gross_total);
                    y = y - height - 5;
                    Bill.count++;

                    addElement(Bill.count);
                }
                }catch(Exception ex)
                {
                    try {
                        ex.printStackTrace(new PrintStream(new File("log.txt")));
                    } catch (FileNotFoundException ex1) {
                        Logger.getLogger(GenerateBill.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        b_add_row[0].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    if (tqty[Bill.count].getText().equals("")||tqty[Bill.count].getText().equals("0")) {
                        JOptionPane.showMessageDialog(frame, "please enter quantity");
                    } else if (ttotal[Bill.count].getText().equals("") && tdiscount[Bill.count].getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "please enter Total or discount");
                    } else {
                        frame.remove(b_add_row[0]);
                        frame.remove(b_gross_total);
                        y = y - height - 5;
                        Bill.count++;
                        addElement(Bill.count);
                    }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        for (int i = 0; i <= Bill.count; i++) {
            int j = i;

            ttotal[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (ttotal[j].getText().equals("")) {
                        ttotal[j].setText(0 + "");
                    } else if (ttotal[j].getText().equals("0")) {

                    } else {
                        countDetail(j);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            c_item[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected_item = c_item[j].getSelectedItem().toString();
                    try {

                        PreparedStatement p = con.prepareStatement("select Iprice,Igst from items where Iname=?");
                        p.setString(1, selected_item);
                        ResultSet r = p.executeQuery();
                        while (r.next()) {
                            price[j] = r.getInt("Iprice");
                            gst[j] = r.getFloat("Igst");
                            
                        }
                        tprice[j].setText(price[j] + "");
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (Exception ex) {

                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            tqty[j].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    String selected_item = c_item[j].getSelectedItem().toString();
                    try {

                        PreparedStatement p = con.prepareStatement("select Iprice,Igst from items where Iname=?");
                        p.setString(1, selected_item);
                        ResultSet r = p.executeQuery();
                        while (r.next()) {
                            price[j] = r.getInt("Iprice");
                            gst[j] = r.getFloat("Igst");
                            
                        }
                        tprice[j].setText(price[j] + "");
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (Exception ex) {

                    }

                }
            });
            tdiscount[j].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (tdiscount[j].getText().equals("")) {
                        tdiscount[j].setText(0 + "");
                    } else {
                        System.out.println("Print");
                        countDetail(j);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

        }
    }

    public void countDetail(int i) {
        float Itotal=0;
        try {
            grand_total = 0;
            sgst_summary=0;
            String selected_item = c_item[i].getSelectedItem().toString();
            if (tqty[i].getText().equals("")) {
                tqty[i].setText("0");
                System.out.println(tqty[i].getText());
            }
            if (ttotal[i].getText().equals("")) {
                ttotal[i].setText("0");
                System.out.println(tqty[i].getText());
            }
            
            //int qty = Integer.parseInt(tqty[i].getText());
            qty[i]=Integer.parseInt(tqty[i].getText());
            //int price = 0;
            PreparedStatement p = con.prepareStatement("select Iprice,Igst from items where Iname=?");
            p.setString(1, selected_item);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                price[i] = r.getInt("Iprice");
                gst[i] = r.getFloat("Igst");
                
            }
           
            if(price[i]==0)
            {
                price[i]=Float.parseFloat(ttotal[i].getText())/Float.parseFloat(tqty[i].getText());
                Itotal=Float.parseFloat(ttotal[i].getText());
                tprice[i].setText(price[i]+"");
            }
            else
            {
            Itotal = qty[i] * price[i];
            tprice[i].setText(price[i] + "");
            }
            //ttotal[i].setText(Itotal + "");
            float total = Float.parseFloat(ttotal[i].getText());
            float discount1 = 0;
            try {
                discount1 = Float.parseFloat(tdiscount[i].getText());
            } catch (Exception e) {
            }
            if (total != 0) {
                discount[i] = 100 - (total * 100 / Itotal);
                tdiscount[i].setText(discount[i] + "");
            } else {
                ttotal[i].setText(Itotal - ((Itotal * discount1) / 100) + "");
            }
            //ttotal[i].setText(Itotal - ((Itotal * Float.parseFloat(tdiscount[i].getText())) / 100) + "");
            //gst[i] = sgst[i] + cgst[i];
            sgst[i]=gst[i]/2;
            cgst[i]=gst[i]/2;
            base_price[i] = ((Float.parseFloat(ttotal[i].getText()) * 100) / (100 + gst[i]))/qty[i];
            System.out.println("base : " + base_price[i]);
            for (int j = 0; j <= Bill.count; j++) {
                // grand_total = grand_total + Integer.parseInt(tqty[j].getText()) * price[j];
                try{
                grand_total = grand_total + Float.parseFloat(ttotal[j].getText());
                sgst_summary=sgst_summary+(sgst[j]*base_price[j]*qty[j])/100;
                }catch(Exception ex)
                {
                
                }
            }
            
            System.out.println("sgst sumary is : "+sgst_summary);
            System.out.println("cgst summary is : "+sgst_summary);
            b_gross_total.setText(grand_total + "");
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fileCombo(int i) {
        try {
            PreparedStatement p = con.prepareStatement("select * from items");
            ResultSet r = p.executeQuery();
            //c_item[i].addItem(" ");
            while (r.next()) {
                System.out.println(r.getString("Iname"));
                //items[i]=r.getString("Iname");
                c_item[i].addItem(r.getString("Iname"));
                //k++;
            }
            r.first();
            tprice[i].setText(r.getFloat("Iprice") + "");
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
