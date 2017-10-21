/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

//import static bill.Billing.con;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.BindException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thanx
 */
public class Bill {
    static int instance;
    static int count=-1;
    int x = 70;
    int y = 70;
    int height = 25;
    int width = 70;
    int x1 = 70;
    int y1 = 70;
    int height1 = 25;
    int width1 = 150;
    float price[] = new float[10];
    float gst[] = new float[10];
    float discount[] = new float[10];
    float base_price[]=new float[10];
    static int frame_height=700;
    static int frame_width=1000;
    
   
    ///form 3
    JFrame f_home = new JFrame("Flexi Billing Solution");

    ///form add_Item
    JFrame f_add_Item = new JFrame("Add Item");

    ///other variables
    float grand_total;
     static Connection con;
    String data[][];
    String column[] = {"No.", "Item Name", "Quantity", "Total"};
    String column1[] = {"Sr.","Item Name","Qty","Base Price", "MRP", "Disc.%", "GST", "Total"};
    static Image img;
    static ServerSocket socket;

    static {
        try {
           /* String command = "cmd /c start c:/xampp/xampp-control-3-beta.exe";
            Process child = Runtime.getRuntime().exec(command);*/
            Class.forName(Db.driver_name);
            con = DriverManager.getConnection(Db.con_string,Db.user_name,Db.password);
            //socket=new ServerSocket(9999,0,InetAddress.getByAddress(new byte[]{127,0,0,1}));
            //System.out.println("success");
            
        } 
        catch (ClassNotFoundException ex) {
            // Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            // Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

   
  
    public void homeFrame() {
        instance=1;
        f_home.setSize(frame_width,frame_height);
        f_home.getContentPane().setBackground(Color.WHITE);
        f_home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
        f_home.setIconImage(img);
        JLabel ltitle=new JLabel("Tax Invoice");
        ltitle.setBounds(frame_width/2-150, 150, 300, 80);
        ltitle.setForeground(Color.red);
        ltitle.setFont(new Font("verdana",Font.BOLD,40));
        JButton b_add_item = new JButton("Add Item");
        b_add_item.setBounds(frame_width/2-100, 230, 120, 40);
        b_add_item.setBackground(Color.red);
        b_add_item.setForeground(Color.WHITE);
        JButton b_generate_bill = new JButton("Generate Bill");
        b_generate_bill.setBounds(frame_width/2-100, 280, 120, 40);
        b_generate_bill.setBackground(Color.green);
        b_generate_bill.setForeground(Color.WHITE);
        JButton b_view = new JButton("View");
        b_view.setBounds(frame_width/2-100, 330,120, 40);
        b_view.setBackground(Color.blue);
        b_view.setForeground(Color.WHITE);
        JButton b_update_item=new JButton("Update Item");
        b_update_item.setBounds(frame_width/2-100, 380,120, 40);
        b_update_item.setBackground(Color.gray);
        b_update_item.setForeground(Color.WHITE);
        f_home.add(b_add_item);
        f_home.add(b_generate_bill);
        f_home.add(b_view);
        f_home.add(ltitle);
        f_home.add(b_update_item);
        f_home.setLayout(null);
        f_home.setVisible(true);
        b_generate_bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //f_home.setVisible(false);
               if(Bill.count==-1) 
                new GenerateBill().initialization();
                // new GenerateBill().fileCombo(0);
                //frame.setVisible(true);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        b_add_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //f_home.setVisible(false);
                //frame.setVisible(true);
                AddItem addItem = new AddItem();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        b_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBills v=new ViewBills();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        b_update_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateItem().updateItem();
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void AddItemFrame() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            /// Declarations
            socket=new ServerSocket(6783,0 ,InetAddress.getByName("localhost"));
            new Credential();
            //Bill b = new Bill();
            // b.initialization();
            
            // b.homeFrame();
            // b.fileCombo();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new Bill().f_home,"Instance already running");
            System.exit(0);
            //Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
   
}
