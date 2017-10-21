/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import static bill.Bill.con;
import static bill.Bill.img;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Thanx
 */
public class ViewBills {

    JFrame f_view_bill = new JFrame("Flexi Billing solution");
    String query_Invoice = "Insert into invoice (Bid,name,Address,Iname,Iprice,discount,base_price,Igst,qty,total) SELECT bill.Bid, bill.name, bill.Address, items.Iname,items.Iprice,purchase.discount,purchase.base_price,items.Igst ,purchase.qty, purchase.total AS Total  FROM items INNER JOIN purchase ON purchase.Iid = items.Iid INNER JOIN bill ON purchase.Bid = bill.Bid ORDER By bill.Bid";
    /*String query = "SELECT bill.Date,bill.Bid, bill.name, bill.Address,bill.phone,items.Iname,items.Iprice,purchase.discount,purchase.base_price,items.Igst,purchase.qty, purchase.total AS \"Total\"\n"
            + "FROM items\n"
            + "INNER JOIN purchase ON purchase.Iid = items.Iid\n"
            + "INNER JOIN bill ON purchase.Bid = bill.Bid ";*/
    String query="select * from invoice";
    PreparedStatement p;
    ResultSet rs;
    Connection con;

    int columnCount;

    int x = 70;
    int x1 = 70;
    int y = 70;
    int y1 = 70;
    int width = 70;
    int height = 40;
    String path = "";
    String column_display[] = {"Date", "Invoice", "name", "Address", "phone", "name", "MRP", "Disc.%", "Base Price", "CGST", "SGST","GST%", "Qty", "Total"};
    String data[][];
    int row;

    JComboBox<String> c_Bid;
    JComboBox<String> c_date;
    JComboBox<String> c_name;
    JComboBox<String> c_Iname;
    JComboBox<String> c_Bid_search;
    JComboBox<String> c_to_date;
    JFileChooser fc_save;
    JTextField tpath;
    JButton b_export_to_excel;
    JButton b_path_chooser;
    JLabel lBid;
    JLabel ldate;
    JLabel lname;
    JLabel l_to_date;
    JLabel lIname;
    JTable tbills;
    JScrollPane sbills;

    public ViewBills() {
        try {

            Class.forName(Db.driver_name);
            con = DriverManager.getConnection(Db.con_string, Db.user_name, Db.password);
            this.p = con.prepareStatement("select Pid from purchase");
            rs = p.executeQuery();
            rs.last();
            row = rs.getRow();

            /*this.p = con.prepareStatement(query + "Order by bill.Bid");
            rs = p.executeQuery();*/
            f_view_bill.setSize(1000, 700);
            f_view_bill.setIconImage(img);
            f_view_bill.getContentPane().setBackground(Color.WHITE);
            
            lBid = new JLabel("Bid ");
            lBid.setBounds(x, y, width, height);

            y1 = y1 + height + 5;
            c_Bid = new JComboBox<>();
            c_Bid.setBounds(x, y1, width, height);

            x1 = x1 + width + 5;
            ldate = new JLabel("Date ");
            ldate.setBounds(x1, y, width, height);
            
            

            c_date = new JComboBox<>();
            c_date.setBounds(x1, y1, width, height);
            
             x1 = x1 + width + 5;
            l_to_date = new JLabel("To Date ");
            l_to_date.setBounds(x1, y, width, height);
            
            

            c_to_date = new JComboBox<>();
            c_to_date.setBounds(x1, y1, width, height);

            x1 = x1 + width + 5;
            lname = new JLabel("Name ");
            lname.setBounds(x1, y, width, height);

            c_name = new JComboBox<>();
            c_name.setBounds(x1, y1, width, height);

            x1 = x1 + width + 5;
            lIname = new JLabel("Item Name ");
            lIname.setBounds(x1, y, width, height);

            c_Iname = new JComboBox<>();
            c_Iname.setBounds(x1, y1, width, height);

            y1 = y1 + height + 5;

            //tbills = new JTable(buildTableModel(rs));
            // data=viewData(query);
            String sql="select * from invoice Order by Bid";
            //String sql = query + "Order by bill.Bid ";
            data = viewData(sql);
            tbills = new JTable(data, column_display);
            tbills.getColumn("Address").setMinWidth(150);
            tbills.getColumn("phone").setMinWidth(100);

            sbills = new JScrollPane(tbills);
            System.out.println(columnCount);
            sbills.setBounds(x, y1, 13 * 70, 400);

            c_Bid_search = new JComboBox();
            c_Bid_search.setBounds(x, y1 + 400, width, height);

            tpath = new JTextField();
            tpath.setBounds(x, y1 + 400 + 5 + height, width + 150, height);

            b_export_to_excel = new JButton("Export");
            b_export_to_excel.setBounds(x + 310, y1 + 400 + height + 5, width + 10, height);

            b_path_chooser = new JButton("Browse");
            b_path_chooser.setBounds(x1 - 80, y1 + 400 + height + 5, width + 10, height);

            findComboByBid();
            findComboBydate();
            findComboByName();
            findComboByIName();

            f_view_bill.add(sbills);
            f_view_bill.add(lBid);
            f_view_bill.add(c_Bid);
            f_view_bill.add(c_date);
            f_view_bill.add(ldate);
             f_view_bill.add(l_to_date);
            f_view_bill.add(c_to_date);
            f_view_bill.add(c_name);
            f_view_bill.add(lname);
            f_view_bill.add(c_Iname);
            f_view_bill.add(lIname);
            f_view_bill.add(c_Bid_search);
            //f_view_bill.add(fc_save);
            f_view_bill.add(b_export_to_excel);
            f_view_bill.add(b_path_chooser);
            f_view_bill.add(tpath);
           
            f_view_bill.setLayout(null);
            f_view_bill.setVisible(true);

            c_Bid.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display();
                    /*String Bid = c_Bid.getSelectedItem() + "%";
                    String date = c_date.getSelectedItem() + "%";
                    String name = c_name.getSelectedItem() + "%";
                    String Iname = c_Iname.getSelectedItem() + "%";
                    /*if(Bid.equals(" "))
                        retrive("%", "%", "%", "%"); 
                    else*/
                    //retrive(Bid, date, name, Iname);
                    //retrive(Bid, "%", "%", "%");

                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            c_Bid_search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String Bid = c_Bid_search.getSelectedItem() + "";
                    new PrintPage().printPage(Bid);

                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            c_name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*String name = c_name.getSelectedItem() + "";
                    if (name.equals(" ")) {
                        retrive("%", "%", "%", "%");
                    } else {
                        retrive("%", "%", name, "%");
                    }*/
                    display();
                }
            });
            c_Iname.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*String Bid = c_Bid.getSelectedItem() + "%";
                    String date = c_date.getSelectedItem() + "%";
                    String name = c_name.getSelectedItem() + "%";
                    String Iname = c_Iname.getSelectedItem() + "%";
                    retrive(Bid, date, name, Iname);*/
 /*String Iname = c_Iname.getSelectedItem() + "";
                    if(Iname.equals(" "))
                        retrive("%", "%", "%", "%"); 
                    else
                    retrive("%","%", "%",Iname);*/
                    display();
                }
            });
            c_date.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /* String date = c_date.getSelectedItem() + "";
                    if (date.equals(" ")) {
                        retrive("%", "%", "%", "%");
                    } else {
                        retrive("%", date, "%", "%");
                    }*/
                    display();
                }
            });
            c_to_date.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_export_to_excel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        p = con.prepareStatement(query + " order by Bid");
                        rs = p.executeQuery();
                        HSSFWorkbook book = new ExportToExcel().dump(rs);
                        ExportToExcel.writeExcel(book, path);
                        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewBills.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            b_path_chooser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fc_save = new JFileChooser();
                    //       fc_save.setBounds(x+200, y, width, height);
                    fc_save.setCurrentDirectory(new java.io.File("."));
                    fc_save.setDialogTitle("destination to store file");
                    fc_save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fc_save.setAcceptAllFileFilterUsed(false);
                    if (fc_save.showOpenDialog(fc_save) == JFileChooser.APPROVE_OPTION) {
                        /* System.out.println("getCurrentDirectory(): "
                                + fc_save.getCurrentDirectory());
                        System.out.println("getSelectedFile() : "
                                + fc_save.getSelectedFile());*/

                        path = fc_save.getSelectedFile() + "";

                        tpath.setText(path);
                        System.out.println(path);
                    } else {
                        System.out.println("No Selection ");
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(f_view_bill, "please start server");
            // Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(f_view_bill, "please start server");
            //Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public void findComboByBid() {
        try {
            p = con.prepareStatement("select Bid from bill");
            ResultSet r = p.executeQuery();
            int i = 0;
            c_Bid.addItem("");
            while (r.next()) {
                //System.out.println("a");
                //items[i]=r.getString("Iname");
                c_Bid.addItem(r.getString("Bid"));
                c_Bid_search.addItem(r.getString("Bid"));
                i++;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void findComboBydate() {
        try {
            p = con.prepareStatement("select DISTINCT date from bill");
            ResultSet r = p.executeQuery();
            int i = 0;
            c_date.addItem("");
            c_to_date.addItem("");
            while (r.next()) {
                //System.out.println("a");
                //items[i]=r.getString("Iname");
                c_date.addItem(r.getString("date"));
                c_to_date.addItem(r.getString("date"));
                i++;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void findComboByName() {
        try {
            p = con.prepareStatement("select DISTINCT name from bill");
            ResultSet r = p.executeQuery();
            int i = 0;
            c_name.addItem("");
            while (r.next()) {
                //System.out.println("a");
                //items[i]=r.getString("Iname");
                c_name.addItem(r.getString("name"));
                i++;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void findComboByIName() {
        try {
            p = con.prepareStatement("select DISTINCT Iname from items");
            ResultSet r = p.executeQuery();
            int i = 0;
            c_Iname.addItem("");
            while (r.next()) {
                //System.out.println("a");
                //items[i]=r.getString("Iname");
                c_Iname.addItem(r.getString("Iname"));
                i++;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void retrive(String Bid, String date, String todate,String name, String Iname) {
       // String sql = query + "where bill.Bid Like \"" + Bid + "\" AND bill.date Like '" + date + "' AND bill.name Like'" + name + "' AND items.Iname Like'" + Iname + "' order by bill.Bid ";
        //String sql = query + " where Bid Like \"" + Bid + "\" AND date Like '" + date + "' AND name Like'" + name + "' AND Iname Like'" + Iname + "' order by Bid ";
        String sql = null;
        
           sql = query + " where Bid Like \"" + Bid + "\" AND date >= '" + date + "' AND date <= '"+todate+"' AND name Like'" + name + "' AND Iname Like'" + Iname + "' order by Bid ";
       
        System.out.println(sql);
        f_view_bill.remove(sbills);
        tbills = new JTable(viewData(sql), column_display);
        sbills = new JScrollPane(tbills);
        sbills.setBounds(x, y1, 13 * 70, 400);
        f_view_bill.add(sbills);
        f_view_bill.revalidate();
        f_view_bill.repaint();
    }

    public void display() {
        String Bid = c_Bid.getSelectedItem() + "%";
        String date;
        
        String todate;
       
        String name = c_name.getSelectedItem() + "%";
        String Iname = c_Iname.getSelectedItem() + "%";
        if(c_date.getSelectedItem().equals(""))
            date="%";
        else
            date=c_date.getSelectedItem()+"";
        if(c_to_date.getSelectedItem().equals(""))
            todate="3%";
        else
            todate=c_to_date.getSelectedItem()+"%";
        retrive(Bid, date,todate, name, Iname);
    }

    public String[][] viewData(String query) {
        String data[][] = null;
        try {
            PreparedStatement p = con.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            rs.last();
            int last = rs.getRow();
            p = con.prepareStatement(query);
            rs = p.executeQuery();
            data = new String[last][column_display.length];
            int counter = 1;
            for (int j = 0; rs.next(); j++) {

                for (int i = 0; i < column_display.length; i++) {
                    //String Bid = rs.getString("bill.Bid");
                    String Bid = rs.getString("Bid");
                    
                    if (i == 0) {
                        if (j > 0) {
                            if (Bid.equals(data[j - counter][1])) {
                                data[j][i] = "";
                            } else {
                                //data[j][i] = rs.getString("bill.Date");
                                 data[j][i] = rs.getString("Date");
                            }

                        } else {
                            //data[j][i] = rs.getString("bill.Date");
                             data[j][i] = rs.getString("Date");
                        }

                    }
                    if (i == 1) {
                        if (j > 0) {
                            if (Bid.equals(data[j - counter][1])) {
                                data[j][i] = "";
                            } else {
                                //data[j][i] = rs.getString("bill.Bid");
                                 data[j][i] = rs.getString("Bid");
                            }
                        } else {
                            //data[j][i] = rs.getInt("bill.Bid") + "";
                            data[j][i] = rs.getString("Bid");
                        }
                        // System.out.println(data[j][i]);

                    }
                    if (i == 2) {
                        if (j > 0) {
                            if (Bid.equals(data[j - counter][1])) {
                                data[j][i] = "";
                            } else {
                                //data[j][i] = rs.getString("bill.name");
                                data[j][i] = rs.getString("name");
                            }
                        } else {
                            //data[j][i] = rs.getString("bill.name");
                            data[j][i] = rs.getString("name");
                        }

                    }
                    if (i == 3) {

                        if (j > 0) {
                            if (Bid.equals(data[j - counter][1])) {
                                data[j][i] = "";
                            } else {
                                //data[j][i] = rs.getString("bill.address");
                                 data[j][i] = rs.getString("address");
                            }
                        } else {
                            //data[j][i] = rs.getString("bill.address") + "";
                            data[j][i] = rs.getString("address");
                        }

                    }
                    if (i == 4) {
                        if (j > 0) {
                            if (Bid.equals(data[j - counter][1])) {
                                data[j][i] = "";
                                counter++;
                            } else {
                                counter = 1;
                               // data[j][i] = rs.getString("bill.phone") + "";
                                data[j][i] = rs.getString("phone") + "";
                            }
                        } else {
                            //data[j][i] = rs.getString("bill.phone") + "";
                            data[j][i] = rs.getString("phone") + "";
                        }
                    }
                    if (i == 5) {
                       // data[j][i] = rs.getString("items.Iname") + "";
                        data[j][i] = rs.getString("Iname") + "";
                    }
                    if (i == 6) {
                       // data[j][i] = rs.getFloat("items.Iprice") + "";
                       data[j][i] = rs.getFloat("Iprice") + "";
                    }
                    if (i == 7) {
                        //data[j][i] = rs.getFloat("purchase.discount") + "";
                        data[j][i] = rs.getFloat("discount") + "";
                    }
                    if (i == 8) {
                       // data[j][i] = rs.getFloat("purchase.base_price") + "";
                        data[j][i] = rs.getFloat("base_price") + "";
                    }
                    if (i == 9) {
                        //data[j][i] = rs.getFloat("items.Igst")/2 + "";
                        data[j][i] = rs.getFloat("Icgst")+ "";
                    }
                    if (i == 10) {
                        data[j][i] = rs.getFloat("Isgst") + "";
                    }
                    if (i == 11) {
                        data[j][i] = rs.getFloat("Igst") + "";
                    }
                    if (i == 12) {
                        data[j][i] = rs.getInt("qty") + "";
                    }
                    if (i == 13) {
                        data[j][i] = rs.getFloat("total") + "";
                    }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewBills.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
