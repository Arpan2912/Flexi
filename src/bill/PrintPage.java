/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import static bill.Bill.con;
import static bill.Bill.img;
import com.sun.javafx.iio.common.ImageTools;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static java.lang.System.gc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thanx
 */
public class PrintPage {

    int x_print = 30;
    int y_print = 30;
    int height_print = 25;
    int width_print = 70;
    int x1_print = 30;
    int y1_print = 30;
    int height1_parent = 25;
    int width1_parent = 150;
    int price_parent[] = new int[10];
    float grand_total;
    String date;
    JFrame frame;
    JFrame f_print = new JFrame("Print");
    JPanel p_print = new JPanel();
    JTextField tname;
    JTextField taddress1;
    JTextField tphone;
    JTextField tgst_no;
    JButton b_print;
    JLabel l_firm_name;
    JLabel l_category;
    JLabel l_name_print;
    JLabel l_name_value;
    JLabel l_address1_print;
    JLabel l_address1_value;
    JLabel l_address2_print;
    JLabel l_address2_value;
    JLabel l_gst_buyer;
    JLabel l_gross_total;
    JLabel l_cgst_total;
    JLabel l_sgst_total;
    JLabel l_gross_value;
    JLabel l_tax_Invoice;
    JLabel l_firm_address1;
    JLabel l_firm_address2;
    JLabel l_contact_detail;
    JLabel l_gst_no;
    JLabel l_tax_border;
    JLabel l_firm_border;
    JLabel l_category_border;
    JLabel l_category_firm;
    JLabel l_category_border_firm;
    JLabel l_invoice_print;
    JLabel l_invoice_value;
    JLabel l_date;
    String data[][];
    String font_style = Font.SANS_SERIF;
    //String column_non_mrp[] = {"Sr.", "Item Name", "Qty", "Base Price", "Disc.%", "CGST", "SGST", "Total"};
    String column_mrp[] = {"Sr.", "Item Name", "Qty", "Base Price", "MRP", "Disc.%", "GST%","CGST", "SGST", "Total"};
    Bill b = new Bill();

    /*public void printPage(JFrame frame, JTextField taddress1, JTextField tphone, JTextField tname, JComboBox[] c_item, JTextField[] tqty, float base_price[], JTextField tprice[], JTextField tdiscount[], float cgst[], float sgst[], JTextField[] ttotal, JButton b_gross_total) {
        this.tname = tname;
        this.taddress1 = taddress1;
        this.tphone = tphone;
        this.frame = frame;
        if (tname.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "please enter name");
        } else {
            System.out.println(ttotal[0].getText());
            grand_total = Float.parseFloat(b_gross_total.getText());
            data = new String[Bill.count + 1][9];                                   //set data to data[][]
            for (int j = 0; j <= Bill.count; j++) {
                if (tqty[j].getText().equals("") || tqty[j].getText().equals("0")) {
                    continue;
                }
                for (int i = 0; i <= 8; i++) {

                    if (i == 0) {
                        data[j][i] = (j + 1) + "";
                    }
                    if (i == 1) {
                        data[j][i] = c_item[j].getSelectedItem().toString();

                    }
                    if (i == 2) {
                        data[j][i] = tqty[j].getText();

                    }
                    if (i == 8) {
                        data[j][i] = ttotal[j].getText();
                        System.out.println(data[j][i]);
                    }
                    if (i == 3) {
                        data[j][i] = base_price[j] + "";
                    }
                    if (i == 4) {
                        data[j][i] = tprice[j].getText();
                    }
                    if (i == 5) {
                        data[j][i] = tdiscount[j].getText();
                    }
                    if (i == 6) {
                        data[j][i] = cgst[j] + "";
                    }
                    if (i == 7) {
                        data[j][i] = sgst[j] + "";
                    }

                }
            }

            f_print.setSize(1000, 1500);                                            //set frame size
            f_print.setIconImage(img);
            // p_print.setBounds(0, 0, 800,1000);
            // p_print.setLayout(new GridBagLayout());
            //GridBagConstraints gbc = new GridBagConstraints();
            // p_print.setLayout(new BoxLayout(p_print, BoxLayout.Y_AXIS));
            f_print.getContentPane().setBackground(Color.WHITE);                    //set background color of frame

            l_tax_Invoice = new JLabel("Tax Invoice");
            l_tax_Invoice.setFont(new Font("verdana", Font.BOLD, 15));
            l_tax_Invoice.setBounds(x_print, y_print, width_print + 30, height_print);

            y_print = y_print + 5;
            l_tax_border = new JLabel("_________________________________________________________________________________________________________________");
            l_tax_border.setBounds(x_print, y_print, 800, height_print);

            ///seller Info Start
//        y_print=y_print+height_print+5;
//        l_category_firm = new JLabel("Seller");                                   //Set name of firm
//        l_category_firm.setBounds(x_print+250+100, y_print, 250, height_print);
//        
//        y_print=y_print+5;
//        l_category_border_firm=new JLabel("........................................................................      ");
//        l_category_border_firm.setBounds(x_print+250, y_print,500, height);
            y_print = y_print + height_print + 5;
            l_firm_name = new JLabel("XYZ Firm");
            l_firm_name.setFont(new Font("verdana", Font.BOLD, 25));//Set name of firm
            l_firm_name.setBounds(x_print, y_print, 250, height_print);     //set position

            y_print = y_print + height_print + 3;
            l_firm_address1 = new JLabel("303,Deepa Complex,near Gujrat gas circle,");                                   //Set address of firm
            l_firm_address1.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + height_print + 3;
            l_firm_address2 = new JLabel("Adajan,surat,395009");                                   //Set address of firm
            l_firm_address2.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + height_print + 3;
            l_contact_detail = new JLabel("Ph No.: 1234569789");                                   //Set address of firm
            l_contact_detail.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + height_print + 3;
            l_gst_no = new JLabel("GSTIN No.: 123456789012345");                                   //Set address of firm
            l_gst_no.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + 8;
            l_firm_border = new JLabel("_________________________________________________________________________________________________________________");
            l_firm_border.setBounds(x_print, y_print, 800, height_print);
            /// Seller Info finish,Buyer Info Start

            y1_print = y1_print + height_print + 10;
            l_category = new JLabel("Buyer");                                   //Set name of firm
            l_category.setBounds(x_print + 430, y1_print, 250, height_print);

//        y1_print=y1_print+5;
//        l_category_border=new JLabel("........................................................................      ");
//        l_category_border.setBounds(x_print, y1_print,500, height);
            y1_print = y1_print + height_print + 5;
            l_name_value = new JLabel(tname.getText() + ",");                                   //Set name of firm
            l_name_value.setFont(new Font("verdana", Font.BOLD, 12));
            l_name_value.setBounds(x_print + 430, y1_print, 250, height_print);     //set position

            y1_print = y1_print + height_print + 3;
            l_address1_value = new JLabel(taddress1.getText() + ",");                                   //Set address of firm
            l_address1_value.setBounds(x_print + 430, y1_print, 250, height_print);

            y1_print = y1_print + height_print + 3;
            l_address2_value = new JLabel(tphone.getText());                                   //Set address of firm
            l_address2_value.setBounds(x_print + 430, y1_print, 250, height_print);

            /*y1_print=y1_print+height_print+3;
        l_contact_detail = new JLabel("Ph No.: 1234569789");                                   //Set address of firm
        l_contact_detail.setBounds(x_print, y1_print,250, height_print);

        y1_print=y1_print+height_print+3;
        l_gst_no = new JLabel("GSTIN No.: 123456789012345");                                   //Set address of firm
        l_gst_no.setBounds(x_print, y1_print,250, height_print);*/
    //Buyer Info finish
//        y_print = y_print + height_print + 5;
//        l_name_print = new JLabel("Name :");
//        l_name_print.setBounds(x_print, y_print, width_print, height_print);
//
//        x_print = x_print + 70;
//        l_name_value = new JLabel(tname.getText());
//        l_name_value.setBounds(x_print, y_print, width_print, height_print);
//        y_print = y_print + height_print + 5;
//        l_address_print = new JLabel("Address :");
//        l_address_print.setBounds(x1_print, y_print, width_print, height_print);
//
//        //x_print=x_print+70;
//        l_address_value = new JLabel(taddress.getText());
//        l_address_value.setBounds(x_print, y_print, width_print, height_print);
    /*        y_print = y_print + 15;
            l_invoice_print = new JLabel("Invoice No.: ");
            l_invoice_print.setBounds(x_print, y_print, width_print, height_print);

            int Bid = 0;
            try {
                String query = "SELECT Bid FROM bill ORDER BY Bid DESC LIMIT 1";
                PreparedStatement p = con.prepareStatement(query);
                ResultSet rs = p.executeQuery();
                rs.next();
                Bid = rs.getInt("Bid") + 1;
            } catch (Exception e) {
            }
            x_print = x_print + width_print + 5;
            l_invoice_value = new JLabel(Bid + "");
            l_invoice_value.setBounds(x_print, y_print, width_print, height_print);

            // x_print=300;
            ///code to get current date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.now();
            date = dtf.format(localDate);

            l_date = new JLabel("Date : " + date);
            l_date.setBounds(750, y_print, width_print + 100, height_print);

            y_print = y_print + height_print + 5;                                   //Table structure, set data to table and display 
            JTable t_print = new JTable(data, column1);
            JTableHeader header = t_print.getTableHeader();                         //get header and set background
            header.setBackground(Color.white);

            t_print.setShowHorizontalLines(false);                                  //remove horizontal border
            //  t_print.getColumnModel().getColumn(0).setWidth(20);
            JScrollPane sp = new JScrollPane(t_print);                              //add table to scroll pane
            sp.setBorder(BorderFactory.createEmptyBorder());                        //remove the border from scroll pane
            // f_print.add(sp, BorderLayout.CENTER);
            sp.getViewport().setBackground(Color.WHITE);                            //background color - Table
            t_print.setRowHeight(30);
            t_print.getColumn("Sr.").setMaxWidth(20);
            t_print.getColumn("Qty").setMaxWidth(30);
            t_print.getColumn("Disc.%").setMaxWidth(50);
            t_print.getColumn("CGST").setMaxWidth(50);
            t_print.getColumn("SGST").setMaxWidth(50);
            t_print.getColumn("Item Name").setMinWidth(200);
            t_print.getColumn("Total").setMaxWidth(200);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            t_print.setDefaultRenderer(String.class, centerRenderer);
            int t_height = 30 * (Bill.count + 2);                                   //Table height counting from inserted data

            sp.setBounds(x1_print, y_print, 800, 30 * (Bill.count + 2));

            y_print = y_print + t_height;
            l_gross_total = new JLabel("Total");
            l_gross_total.setBounds(70, y_print, width_print, height_print);

            l_gross_value = new JLabel(b_gross_total.getText());
            l_gross_value.setBounds(x_print + 670, y_print, width_print, height_print);

            JButton b_print = new JButton("Print");
            b_print.setBounds(x_print, y_print + height_print + 5, width_print, height_print);

            f_print.add(l_firm_name);
            f_print.add(l_name_value);

            f_print.add(l_category);
            //f_print.add(l_category_border);
            //f_print.add(l_category_firm);
            // f_print.add(l_category_border_firm);
            f_print.add(l_address1_value);
            f_print.add(l_address2_value);
            f_print.add(sp);
            f_print.add(l_gross_total);
            f_print.add(l_gross_value);
            f_print.add(b_print);
            f_print.add(l_tax_Invoice);
            f_print.add(l_firm_address1);
            f_print.add(l_firm_address2);
            f_print.add(l_gst_no);
            f_print.add(l_contact_detail);
            f_print.add(l_tax_border);
            f_print.add(l_firm_border);
            f_print.add(l_invoice_print);
            f_print.add(l_invoice_value);
            f_print.add(l_date);
            f_print.setLayout(null);
            f_print.setVisible(true);
            // frame.setVisible(false);

            b_print.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b_print.setVisible(false);
                    printComponent(frame, c_item, tqty, ttotal, tdiscount, base_price);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_print.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }*/
    public void printPage(String Bid) {
        try {
            String name = "";
            String address = "";
            String phone = "";
            String date = "";
            String gst_no = null;
            String total = "";
            float sgst_total=0;
           /* String query = "SELECT items.Iprice,bill.gst_no,bill.Date,bill.total,bill.Date,bill.Bid, bill.name, bill.Address,bill.phone,items.Iname,items.Iprice,purchase.discount,purchase.base_price,items.Igst,purchase.qty, purchase.total AS \"Total\"\n"
                    + "FROM items\n"
                    + "INNER JOIN purchase ON purchase.Iid = items.Iid\n"
                    + "INNER JOIN bill ON purchase.Bid = bill.Bid where bill.Bid=" + Bid;*/
           String query="select * from invoice where Bid="+Bid;
            PreparedStatement p = con.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            rs.last();
            int last = rs.getRow();
            data = new String[last][10];
            p = con.prepareStatement(query);
            rs = p.executeQuery();
            
            for (int j = 0; rs.next(); j++) {
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getString("phone") + "";
                date = rs.getString("date");
                Bid = rs.getInt("Bid") + "";
                total = String.format("%.2f",rs.getFloat("gtotal")) + "";
                gst_no=rs.getString("gst_no")+"";
                float gst=rs.getFloat("Igst");
                 sgst_total=rs.getFloat("sgst_total");
                
                for (int i = 0; i <= 9; i++) {

                    if (i == 0) {
                        data[j][i] = (j + 1) + "";
                    }
                    if (i == 1) {
                        data[j][i] = rs.getString("Iname");
                        System.out.println(data[j][i]);

                    }
                    if (i == 2) {
                        data[j][i] = rs.getInt("qty") + "";

                    }
                    
                    if (i == 3) {
                        data[j][i] = String.format("%.2f",rs.getFloat("base_price")) ;
                    }
                    if (i == 4) {
                        data[j][i] = String.format("%.2f", rs.getFloat("Iprice"));
                    }
                    if (i == 5) {
                        data[j][i] = String.format("%.2f",rs.getFloat("discount"));
                    }
                    if (i == 6) {
                        data[j][i] = gst+"";
                    }
                    if (i == 7) {
                        data[j][i] = gst/2+"";
                    }
                    if (i == 8) {
                        data[j][i] = gst/2+"";
                    }
                    if (i == 9) {
                        data[j][i] = rs.getFloat("total") + "";
                        //System.out.println(data[j][i]);
                    }

                }
            }

            f_print.setSize(600, 1500);                                            //set frame size
            f_print.setIconImage(img);
            // p_print.setBounds(0, 0, 800,1000);
            // p_print.setLayout(new GridBagLayout());
            //GridBagConstraints gbc = new GridBagConstraints();
            // p_print.setLayout(new BoxLayout(p_print, BoxLayout.Y_AXIS));
            f_print.getContentPane().setBackground(Color.WHITE);                    //set background color of frame

            l_tax_Invoice = new JLabel("Tax Invoice");
            l_tax_Invoice.setFont(new Font(font_style, Font.BOLD, 15));
            l_tax_Invoice.setBounds(x_print, y_print, width_print + 30, height_print);

            y_print = y_print + 5;
            l_tax_border = new JLabel("_______________________________________________________________________________");
            l_tax_border.setBounds(x_print, y_print, 800 / 2 + 155, height_print);

            ///seller Info Start
//        y_print=y_print+height_print+5;
//        l_category_firm = new JLabel("Seller");                                   //Set name of firm
//        l_category_firm.setBounds(x_print+250+100, y_print, 250, height_print);
//        
//        y_print=y_print+5;
//        l_category_border_firm=new JLabel("........................................................................      ");
//        l_category_border_firm.setBounds(x_print+250, y_print,500, height);
            y_print = y_print + height_print + 5;
            l_firm_name = new JLabel("Krishna Stationary & xerox");
            l_firm_name.setFont(new Font(font_style, Font.BOLD, 17));//Set name of firm
            l_firm_name.setBounds(x_print, y_print, 250, height_print);     //set position

            y_print = y_print + height_print;
            l_firm_address1 = new JLabel("shop-7,Virat Apartment,Diwalibag");                                   //Set address of firm
            l_firm_address1.setFont(new Font(font_style, Font.PLAIN, 8));
            l_firm_address1.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + height_print - 7;
            l_firm_address2 = new JLabel("Athwaget,surat,395001");                                   //Set address of firm
            l_firm_address2.setFont(new Font(font_style, Font.PLAIN, 8));
            l_firm_address2.setBounds(x_print, y_print, 250, height_print);

          /*  y_print = y_print + height_print - 7;
            l_contact_detail = new JLabel("Ph No.: ");                                   //Set address of firm
            l_contact_detail.setFont(new Font(font_style, Font.PLAIN, 8));
            l_contact_detail.setBounds(x_print, y_print, 250, height_print);*/

            y_print = y_print + height_print - 7;
            l_gst_no = new JLabel("GSTIN No.: 24BPBPP3962B1Z6");                                   //Set address of firm
            l_gst_no.setFont(new Font(font_style, Font.PLAIN, 8));
            l_gst_no.setBounds(x_print, y_print, 250, height_print);
             y_print = y_print + height_print - 7;

            y_print = y_print + 8;
            l_firm_border = new JLabel("_______________________________________________________________________________");
            l_firm_border.setBounds(x_print, y_print, 800 / 2 + 155, height_print);
            /// Seller Info finish,Buyer Info Start

            y1_print = y1_print + height_print + 10;
            l_category = new JLabel("Buyer");                                   //Set name of firm
            l_category.setFont(new Font(font_style, Font.BOLD, 12));
            l_category.setBounds(x_print + 320, y1_print, 250, height_print);

//        y1_print=y1_print+5;
//        l_category_border=new JLabel("........................................................................      ");
//        l_category_border.setBounds(x_print, y1_print,500, height);
            y1_print = y1_print + height_print;
            l_name_value = new JLabel(name + ",");                                   //Set name of firm
            l_name_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_name_value.setBounds(x_print + 320, y1_print, 250, height_print);     //set position

            y1_print = y1_print + height_print - 7;
            l_address1_value = new JLabel(address + ",");                                   //Set address of firm
            l_address1_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_address1_value.setBounds(x_print + 320, y1_print, 250, height_print);

            y1_print = y1_print + height_print - 7;
            l_address2_value = new JLabel(phone);                                   //Set address of firm
            l_address2_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_address2_value.setBounds(x_print + 320, y1_print, 250, height_print);
            
             y1_print = y1_print + height_print - 7;
             if(gst_no.equals(""))
                 l_gst_buyer = new JLabel();  
             else
                l_gst_buyer = new JLabel("GSTIN NO.: "+gst_no);                                   //Set address of firm
          
            l_gst_buyer.setFont(new Font(font_style, Font.PLAIN, 8));
            l_gst_buyer.setBounds(x_print + 320, y1_print, 250, height_print);

            y_print = y_print + 15;
            l_invoice_print = new JLabel("Invoice No.: ");
            l_invoice_print.setFont(new Font(font_style, Font.BOLD, 8));
            l_invoice_print.setBounds(x_print, y_print, width_print, height_print);

            x_print = x_print + width_print - 20;
            l_invoice_value = new JLabel(Bid + "");
            l_invoice_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_invoice_value.setBounds(x_print, y_print, width_print, height_print);

            l_date = new JLabel("Date : " + date);
            l_date.setFont(new Font(font_style, Font.PLAIN, 8));
            l_date.setBounds(500, y_print, width_print + 100, height_print);

            y_print = y_print + height_print + 5;                                   //Table structure, set data to table and display 
            JTable t_print = new JTable(data, column_mrp);
            JTableHeader header = t_print.getTableHeader();
            header.setFont(new Font(font_style, Font.BOLD, 8));
            //get header and set background
            header.setBackground(Color.white);

            t_print.setShowHorizontalLines(false);                                  //remove horizontal border
            //  t_print.getColumnModel().getColumn(0).setWidth(20);
            JScrollPane sp = new JScrollPane(t_print);                              //add table to scroll pane
            sp.setBorder(BorderFactory.createEmptyBorder());                        //remove the border from scroll pane
            // f_print.add(sp, BorderLayout.CENTER);
            sp.getViewport().setBackground(Color.WHITE);                            //background color - Table
            t_print.setFont(new Font(font_style, Font.PLAIN, 8));
            t_print.setRowHeight(15);
            t_print.getColumn("Sr.").setMaxWidth(10);
            t_print.getColumn("Qty").setMaxWidth(23);
            t_print.getColumn("Disc.%").setMaxWidth(35);
            t_print.getColumn("MRP").setMaxWidth(34);
             t_print.getColumn("GST%").setMaxWidth(25);
            t_print.getColumn("CGST").setMaxWidth(45);
            t_print.getColumn("SGST").setMaxWidth(45);
            t_print.getColumn("Item Name").setMinWidth(80);
            t_print.getColumn("Total").setMaxWidth(60);
            t_print.getColumn("Base Price").setMaxWidth(50);
            //t_print.setRowHeight(20);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            t_print.setDefaultRenderer(String.class, centerRenderer);
            int t_height = 15 * (last + 2);                                   //Table height counting from inserted data

            sp.setBounds(x1_print, y_print, 800 / 2 + 130, 15 * (last + 2));

            /*l_gross_value = new JLabel(total);
            l_gross_value.setBounds(x_print + 670, y_print, width_print, height_print);*/

            y_print = y_print + t_height;
            l_gross_total = new JLabel("Total");
            l_gross_total.setFont(new Font(font_style, Font.BOLD, 10));
            l_gross_total.setBounds(30, y_print, width_print, height_print);

            /// value updation is remain
            l_cgst_total = new JLabel(String.format("%.2f", sgst_total)+"");
            l_cgst_total.setFont(new Font(font_style, Font.BOLD, 8));
            l_cgst_total.setBounds(x_print + 330, y_print, width_print, height_print);
            
            l_sgst_total = new JLabel(String.format("%.2f",sgst_total)+"");
            l_sgst_total.setFont(new Font(font_style, Font.BOLD, 8));
            l_sgst_total.setBounds(x_print + 380, y_print, width_print, height_print);
            
            l_gross_value = new JLabel(total);
            l_gross_value.setFont(new Font(font_style, Font.BOLD, 8));
            l_gross_value.setBounds(x_print + 440, y_print, width_print, height_print);

            b_print = new JButton("Print");
            b_print.setBounds(x_print, y_print + height_print + 5, width_print, height_print);

            f_print.add(l_firm_name);
            f_print.add(l_name_value);

            f_print.add(l_category);
            //f_print.add(l_category_border);
            //f_print.add(l_category_firm);
            // f_print.add(l_category_border_firm);
            f_print.add(l_address1_value);
            f_print.add(l_address2_value);
            f_print.add(sp);
            f_print.add(l_gross_total);
            f_print.add(l_gross_value);
            f_print.add(l_cgst_total);
            f_print.add(l_sgst_total);
            f_print.add(b_print);
            f_print.add(l_tax_Invoice);
            f_print.add(l_firm_address1);
            f_print.add(l_firm_address2);
            f_print.add(l_gst_no);
//            f_print.add(l_contact_detail);
            f_print.add(l_gst_buyer);
            f_print.add(l_tax_border);
            f_print.add(l_firm_border);
            f_print.add(l_invoice_print);
            f_print.add(l_invoice_value);
            f_print.add(l_date);
            //f_print.setLayout(null);

            f_print.setLayout(new BorderLayout(5, 5));

            f_print.setVisible(true);
            b_print.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b_print.setVisible(false);
                    printComponent();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(PrintPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printComponent() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                System.out.println("pf is : " + pf.getImageableX() + " y is : " + pf.getImageableY());
                g2.translate(pf.getImageableX(), pf.getImageableY());
                f_print.paint(g2);
                return Printable.PAGE_EXISTS;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        if (pj.printDialog() == false) {
            return;

        }

        try {
            pj.print();
             f_print.setVisible(false);
        } catch (PrinterException ex) {
            Logger.getLogger(PrintPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printComponent(JFrame frame, JComboBox c_item[], JTextField tqty[], JTextField ttotal[], JTextField tdiscount[], float base_price[]) {

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");
        double margin = 0;
        //Paper paper=new Paper();

        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                // f_print.print(JTable.PrintMode.NORMAL,);
                Graphics2D g2 = (Graphics2D) pg;
                //pf.setPaper(paper);             
                /*Paper p = pf.getPaper();
                 p.setImageableArea(0, 0,pf.getWidth(), pf.getHeight());
                 pf.setPaper(p);*/
                //PageFormat pf2 = printJob.validatePage(pf1);
                g2.translate(pf.getImageableX(), pf.getImageableY());

                f_print.paint(g2);
               
                return Printable.PAGE_EXISTS;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
            String query;
            if (grand_total > 500) {
                query = "insert into bill (`name`,`Address`,`phone`,`total`,`Date`,`sgst_total`,`gst_no`) values (?,?,?,?,?,?,?)";
            } else {
                query = "insert into cash_bill (`name`,`Address`,`phone`,`total`,`Date`,`sgst_total`) values (?,?,?,?,?,?)";
            }
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, tname.getText());
            p.setString(2, taddress1.getText());
            p.setString(3, tphone.getText());
            p.setFloat(4, grand_total);
            p.setString(5, date);
            p.setFloat(6,GenerateBill.sgst_summary);
           if(grand_total>500)
               p.setString(7,tgst_no.getText());
            p.executeUpdate();
            if (grand_total >500) {
                query = "SELECT Bid FROM bill ORDER BY Bid DESC LIMIT 1";
            } else {
                query = "SELECT Bid1 FROM cash_bill ORDER BY Bid1 DESC LIMIT 1";
            }
            p = con.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            rs.next();
            int Bid;
            if(grand_total>500)
                Bid = rs.getInt("Bid");
            else
                Bid = rs.getInt("Bid1");
            for (int i = 0; i <= Bill.count; i++) {
                query = "SELECT Iid,Iprice,Igst FROM items where Iname=?";
                if (tqty[i].getText().equals("0") || tqty[i].getText().equals("")) {
                    continue;
                }
                p = con.prepareStatement(query);
                p.setString(1, c_item[i].getSelectedItem().toString());
                rs = p.executeQuery();
                rs.next();

                int Iid = rs.getInt("Iid");
                float Iprice=rs.getFloat("Iprice");
                float gst=rs.getFloat("Igst");
                float sgst=gst/2;
                float cgst=gst/2;
                if (grand_total > 500) {
                    query = "insert into purchase (`Bid`,`Iid`,`qty`,`total`,`discount`,`base_price`) values (?,?,?,?,?,?)";
                } else {
                    query = "insert into cashmemo (`Bid1`,`Iid`,`qty`,`total`,`discount`,`base_price`) values (?,?,?,?,?,?)";
                }

                p = con.prepareStatement(query);
                p.setInt(1, Bid);
                p.setInt(2, Iid);
                p.setInt(3, Integer.parseInt(tqty[i].getText()));
                p.setFloat(4, Float.parseFloat(ttotal[i].getText()));
                p.setFloat(5, Float.parseFloat(tdiscount[i].getText()));
                p.setFloat(6, base_price[i]);
                p.executeUpdate();

                query="update items set stock=stock-'"+Integer.parseInt(tqty[i].getText())+"'where Iname='"+c_item[i].getSelectedItem().toString()+"'";
                 p = con.prepareStatement(query);
                 p.executeUpdate();
                //String query_Invoice = "Insert into invoice (Bid,name,Address,IName,IPrice,discount,base_price,Igst,qty,total) SELECT bill.Bid, bill.name, bill.Address, items.Iname,items.Iprice,purchase.discount,purchase.base_price,items.Igst ,purchase.qty, purchase.total AS Total  FROM items INNER JOIN purchase ON purchase.Iid = items.Iid INNER JOIN bill ON purchase.Bid = bill.Bid where purchase.Bid=?";
               if(grand_total>500)
               {
                String query_Invoice = "Insert into invoice (Bid,name,Address,IName,IPrice,discount,base_price,Igst,Isgst,Icgst,qty,total,date,phone,gst_no,gtotal,sgst_total) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                 p = con.prepareStatement(query_Invoice);
                 p.setInt(1,Bid);
                p.setString(2,tname.getText()+"");
                p.setString(3, taddress1.getText()+"");
                p.setString(4,c_item[i].getSelectedItem().toString());
                p.setFloat(5,Iprice);
                p.setFloat(6,Float.parseFloat(tdiscount[i].getText()+""));
                p.setFloat(7,base_price[i]);
                p.setFloat(8,gst);
                p.setFloat(9, sgst);
                p.setFloat(10, cgst);
                p.setInt(11,Integer.parseInt(tqty[i].getText()));
                p.setFloat(12,Float.parseFloat(ttotal[i].getText()));
                p.setString(13, date);
                p.setString(14,tphone.getText());
                p.setString(15,tgst_no.getText());
                p.setFloat(16,grand_total);
                p.setFloat(17,GenerateBill.sgst_summary);
                p.executeUpdate();
               } 
                f_print.setVisible(false);
                //GenerateBill b=new GenerateBill();
                frame.setVisible(false);

            }
            Bill.count=-1;
        } catch (PrinterException ex) {
            ex.printStackTrace();
            // handle exception
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printPage(String type, JFrame frame, JTextField taddress1, JTextField tphone,JTextField tgst_no, JTextField tname, JComboBox[] c_item, JTextField[] tqty, float base_price[], JTextField tprice[], JTextField tdiscount[], float cgst[], float sgst[], JTextField[] ttotal, JButton b_gross_total) {
        this.tname = tname;
        this.taddress1 = taddress1;
        this.tphone = tphone;
        this.frame = frame;
        this.tgst_no=tgst_no;
        if (tname.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "please enter name");
        } else {
            System.out.println(ttotal[0].getText());
            grand_total = Float.parseFloat(b_gross_total.getText());
            data = new String[Bill.count + 1][10];                                   //set data to data[][]
            for (int j = 0; j <= Bill.count; j++) {
                if (tqty[j].getText().equals("") || tqty[j].getText().equals("0")) {
                    continue;
                }
                for (int i = 0; i <= 9; i++) {

                    if (i == 0) {
                        data[j][i] = (j + 1) + "";
                    }
                    if (i == 1) {
                        data[j][i] = c_item[j].getSelectedItem().toString();

                    }
                    if (i == 2) {
                        data[j][i] = tqty[j].getText();

                    }
                    
                    if (i == 3) {
                        data[j][i] = String.format("%.2f",base_price[j]) + "";
                    }
                    if (i == 4) {
                        data[j][i] = String.format("%.2f",Float.parseFloat(tprice[j].getText()))+"";
                    }
                    if (i == 5) {
                        data[j][i] = String.format("%.2f",Float.parseFloat(tdiscount[j].getText()))+"";
                    }
                    if (i == 6) {
                        data[j][i] = cgst[j] + sgst[j]+"";
                    }
                    if (i == 7) {
                        data[j][i] = cgst[j] + "";
                    }
                    if (i == 8) {
                        data[j][i] = sgst[j] + "";
                    }
                    if (i == 9) {
                        data[j][i] = String.format("%.2f",Float.parseFloat(ttotal[j].getText()))+"";
                        System.out.println(data[j][i]);
                    }

                }
            }

            f_print.setSize(600, 1500);                                            //set frame size
            f_print.setIconImage(img);
            // p_print.setBounds(0, 0, 800,1000);
            // p_print.setLayout(new GridBagLayout());
            //GridBagConstraints gbc = new GridBagConstraints();
            // p_print.setLayout(new BoxLayout(p_print, BoxLayout.Y_AXIS));
            f_print.getContentPane().setBackground(Color.WHITE);                    //set background color of frame

            l_tax_Invoice = new JLabel(type + " Invoice");
            l_tax_Invoice.setFont(new Font(font_style, Font.BOLD, 15));
            l_tax_Invoice.setBounds(x_print, y_print, width_print + 30, height_print);

            y_print = y_print + 5;
            l_tax_border = new JLabel("____________________________________________________________________________");
            l_tax_border.setBounds(x_print, y_print, 800 / 2 + 135, height_print);

            ///seller Info Start
//        y_print=y_print+height_print+5;
//        l_category_firm = new JLabel("Seller");                                   //Set name of firm
//        l_category_firm.setBounds(x_print+250+100, y_print, 250, height_print);
//        
//        y_print=y_print+5;
//        l_category_border_firm=new JLabel("........................................................................      ");
//        l_category_border_firm.setBounds(x_print+250, y_print,500, height);
            y_print = y_print + height_print + 5;
            l_firm_name = new JLabel("Krishna Stationary & xerox");
            l_firm_name.setFont(new Font(font_style, Font.BOLD, 17));//Set name of firm
            l_firm_name.setBounds(x_print, y_print, 250, height_print);     //set position

            y_print = y_print + height_print;
            l_firm_address1 = new JLabel("shop-7,Virat Apartment,Diwalibag,");                                   //Set address of firm
            l_firm_address1.setFont(new Font(font_style, Font.PLAIN, 8));
            l_firm_address1.setBounds(x_print, y_print, 250, height_print);

            y_print = y_print + height_print - 7;
            l_firm_address2 = new JLabel("Athwaget,surat,395001");                                   //Set address of firm
            l_firm_address2.setFont(new Font(font_style, Font.PLAIN, 8));
            l_firm_address2.setBounds(x_print, y_print, 250, height_print);

           /* y_print = y_print + height_print - 7;
            l_contact_detail = new JLabel("Ph No.: 1234569789");                                   //Set address of firm
            l_contact_detail.setFont(new Font(font_style, Font.PLAIN, 8));
            l_contact_detail.setBounds(x_print, y_print, 250, height_print);*/

            y_print = y_print + height_print - 7;
            l_gst_no = new JLabel("GSTIN No.: 24BPBPP3962B1Z6");                                   //Set address of firm
            l_gst_no.setFont(new Font(font_style, Font.PLAIN, 8));
            l_gst_no.setBounds(x_print, y_print, 250, height_print);
            y_print = y_print + height_print - 7;

            y_print = y_print + 8;
            l_firm_border = new JLabel("____________________________________________________________________________");
            l_firm_border.setBounds(x_print, y_print, 800 / 2 + 135, height_print);
            /// Seller Info finish,Buyer Info Start

            y1_print = y1_print + height_print + 10;
            l_category = new JLabel("Buyer");                                   //Set name of firm
            l_category.setFont(new Font(font_style, Font.BOLD, 12));
            l_category.setBounds(x_print + 320, y1_print, 250, height_print);

//        y1_print=y1_print+5;
//        l_category_border=new JLabel("........................................................................      ");
//        l_category_border.setBounds(x_print, y1_print,500, height);
            y1_print = y1_print + height_print;
            l_name_value = new JLabel(tname.getText() + ",");                                   //Set name of firm
            l_name_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_name_value.setBounds(x_print + 320, y1_print, 250, height_print);     //set position

            y1_print = y1_print + height_print - 7;
            l_address1_value = new JLabel(taddress1.getText() + ",");                                   //Set address of firm
            l_address1_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_address1_value.setBounds(x_print + 320, y1_print, 250, height_print);

            y1_print = y1_print + height_print - 7;
            l_address2_value = new JLabel(tphone.getText());                                   //Set address of firm
            l_address2_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_address2_value.setBounds(x_print + 320, y1_print, 250, height_print);

            
           
            y1_print = y1_print + height_print - 7;
            if(grand_total>500)
                if(tgst_no.getText().equals(""))
                    l_gst_buyer = new JLabel(); 
                else
                   l_gst_buyer = new JLabel("GSTIN NO.: "+tgst_no.getText());                                   //Set address of firm
            else
            l_gst_buyer = new JLabel();  
            l_gst_buyer.setFont(new Font(font_style, Font.PLAIN, 8));
            l_gst_buyer.setBounds(x_print + 320, y1_print, 250, height_print);
           
            /*y1_print=y1_print+height_print+3;
        l_contact_detail = new JLabel("Ph No.: 1234569789");                                   //Set address of firm
        l_contact_detail.setBounds(x_print, y1_print,250, height_print);

        y1_print=y1_print+height_print+3;
        l_gst_no = new JLabel("GSTIN No.: 123456789012345");                                   //Set address of firm
        l_gst_no.setBounds(x_print, y1_print,250, height_print);*/
            //Buyer Info finish
//        y_print = y_print + height_print + 5;
//        l_name_print = new JLabel("Name :");
//        l_name_print.setBounds(x_print, y_print, width_print, height_print);
//
//        x_print = x_print + 70;
//        l_name_value = new JLabel(tname.getText());
//        l_name_value.setBounds(x_print, y_print, width_print, height_print);
//        y_print = y_print + height_print + 5;
//        l_address_print = new JLabel("Address :");
//        l_address_print.setBounds(x1_print, y_print, width_print, height_print);
//
//        //x_print=x_print+70;
//        l_address_value = new JLabel(taddress.getText());
//        l_address_value.setBounds(x_print, y_print, width_print, height_print);
            y_print = y_print + 15;
            l_invoice_print = new JLabel("Invoice No.: ");
            l_invoice_print.setFont(new Font(font_style, Font.BOLD, 8));
            l_invoice_print.setBounds(x_print, y_print, width_print, height_print);

            int Bid = 1;
            try {
                String query;
                if (grand_total >500) {
                    query = "SELECT Bid FROM bill ORDER BY Bid DESC LIMIT 1";
                } else {
                    query = "SELECT Bid1 FROM cash_bill ORDER BY Bid1 DESC LIMIT 1";
                }
                
                PreparedStatement p = con.prepareStatement(query);
                ResultSet rs = p.executeQuery();
                rs.next();
                if(grand_total>500)
                    Bid = rs.getInt("Bid") + 1;
                else
                    Bid = rs.getInt("Bid1") + 1;   
            } catch (Exception e) {
            }
            x_print = x_print + width_print - 20;
            l_invoice_value = new JLabel(Bid + "");
            l_invoice_value.setFont(new Font(font_style, Font.PLAIN, 8));
            l_invoice_value.setBounds(x_print, y_print, width_print, height_print);

            // x_print=300;
            ///code to get current date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.now();
            date = dtf.format(localDate);

            l_date = new JLabel("Date : " + date);
            l_date.setFont(new Font(font_style, Font.PLAIN, 8));
            l_date.setBounds(500, y_print, width_print + 100, height_print);

            y_print = y_print + height_print + 5;                                   //Table structure, set data to table and display 

            JTable t_print = new JTable(data, column_mrp);
            JTableHeader header = t_print.getTableHeader();
            header.setFont(new Font(font_style, Font.BOLD, 8));
            //get header and set background
            header.setBackground(Color.white);

            t_print.setShowHorizontalLines(false);                                  //remove horizontal border
            //  t_print.getColumnModel().getColumn(0).setWidth(20);
            JScrollPane sp = new JScrollPane(t_print);                              //add table to scroll pane
            sp.setBorder(BorderFactory.createEmptyBorder());                        //remove the border from scroll pane
            // f_print.add(sp, BorderLayout.CENTER);
            t_print.setRowHeight(15);
            sp.getViewport().setBackground(Color.WHITE);                            //background color - Table
            t_print.setFont(new Font(font_style, Font.PLAIN, 8));
            t_print.setRowHeight(15);
            t_print.getColumn("Sr.").setMaxWidth(10);
            t_print.getColumn("Qty").setMaxWidth(23);
            t_print.getColumn("Disc.%").setMaxWidth(35);
            t_print.getColumn("MRP").setMaxWidth(34);
            t_print.getColumn("CGST").setMaxWidth(45);
            t_print.getColumn("GST%").setMaxWidth(25);
            t_print.getColumn("SGST").setMaxWidth(45);
            t_print.getColumn("Item Name").setMinWidth(80);
            t_print.getColumn("Total").setMaxWidth(60);
            t_print.getColumn("Base Price").setMaxWidth(50);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            t_print.setDefaultRenderer(String.class, centerRenderer);
            int t_height = 15 * (Bill.count + 2);                                   //Table height counting from inserted data

            sp.setBounds(x1_print, y_print, 800 / 2 + 140, 15 * (Bill.count + 2));

            y_print = y_print + t_height;
            l_gross_total = new JLabel("Total");
            l_gross_total.setFont(new Font(font_style, Font.BOLD, 10));
            l_gross_total.setBounds(30, y_print, width_print, height_print);

             l_cgst_total = new JLabel( String.format("%.2f",GenerateBill.sgst_summary)+"");
            l_cgst_total.setFont(new Font(font_style, Font.BOLD, 8));
            l_cgst_total.setBounds(x_print + 340, y_print, width_print, height_print);
            
            l_sgst_total = new JLabel(String.format("%.2f",GenerateBill.sgst_summary)+"");
            l_sgst_total.setFont(new Font(font_style, Font.BOLD, 8));
            l_sgst_total.setBounds(x_print + 390, y_print, width_print, height_print);
            
            l_gross_value = new JLabel(String.format("%.2f",Float.parseFloat(b_gross_total.getText()))+"");
            l_gross_value.setFont(new Font(font_style, Font.BOLD, 8));
            l_gross_value.setBounds(x_print + 450, y_print, width_print, height_print);

            JButton b_print = new JButton("Print");
            b_print.setBounds(x_print, y_print + height_print + 5, width_print, height_print);

            f_print.add(l_firm_name);
            f_print.add(l_name_value);

            f_print.add(l_category);
            //f_print.add(l_category_border);
            //f_print.add(l_category_firm);
            // f_print.add(l_category_border_firm);
            f_print.add(l_address1_value);
            f_print.add(l_address2_value);
            f_print.add(sp);
            f_print.add(l_gross_total);
            f_print.add(l_cgst_total);
            f_print.add(l_sgst_total);
            f_print.add(l_gross_value);
            f_print.add(b_print);
            f_print.add(l_tax_Invoice);
            f_print.add(l_firm_address1);
            f_print.add(l_firm_address2);
            f_print.add(l_gst_no);
           // f_print.add(l_contact_detail);
            f_print.add(l_gst_buyer);
            f_print.add(l_tax_border);
            f_print.add(l_firm_border);
            f_print.add(l_invoice_print);
            f_print.add(l_invoice_value);
            f_print.add(l_date);
            //f_print.setLayout(null);

            f_print.setLayout(new BorderLayout(5, 5));

            f_print.setVisible(true);
            // frame.setVisible(false);

            b_print.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b_print.setVisible(false);
                    printComponent(frame, c_item, tqty, ttotal, tdiscount, base_price);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_print.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }
}
