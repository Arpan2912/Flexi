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
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanx
 */
public class UpdateItem {
    int x=20;
    int y=100;
    int height=30;
    int width=70;
    int cwidth=200;
    int x1;
    int x2;
    int x3;
    int x4;
    int x5;
    int dif=100;
    int y1=100;
    int height1=70;
    int width1=70;
    int columnCount;
    
    JFrame f_update_item;
    JLabel l_update_item;
    JLabel l_price;
    JLabel l_name;
    JLabel l_gst;
    JLabel l_stock;
    JLabel l_add_stock;
    JLabel l_retail_stock;    
    JComboBox<String> c_item;
    JComboBox<String> c_add_stock;
    JComboBox<String> c_retail_stock;
   
    JTextField t_name;
    JTextField t_price;
    JTextField t_gst;
    JTextField t_stock;
    JTextField t_add_stock;
    JTextField t_retail_stock;
    JButton b_update;
    JButton b_add_stock;
     JButton b_retail_stock;
     JTable t_item_view;
     JScrollPane sbills;
    
    Connection con;

    public static void main(String[] args) {
        new UpdateItem().updateItem();
    }
    public UpdateItem() {
    }
    
    public void updateItem()
    {
        try {
            Class.forName(Db.driver_name);
            con=DriverManager.getConnection(Db.con_string);
            
            String query="SELECT Iname as Name,Iprice as Price,Igst as GST,stock FROM `items`";
            PreparedStatement p=con.prepareStatement(query);
            ResultSet rs=p.executeQuery();
            
            
            
            
            f_update_item=new JFrame("Flexi billing Solution");
            f_update_item.setSize(1000,1000);    
            f_update_item.getContentPane().setBackground(Color.white);
            f_update_item.setIconImage(img);
            
            l_update_item=new JLabel("Update Item");
            l_update_item.setBounds(x+200, y, width+200, height);
            l_update_item.setForeground(Color.red);
            l_update_item.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
            
            y=y+height+5;
            x1=x+cwidth+5;
            l_name=new JLabel("Item Name");
            l_name.setBounds(x1, y, width, height);
            l_name.setForeground(Color.red);
            
            x2=x1+width+5;
            l_price=new JLabel("Item Price");
            l_price.setBounds(x2, y, width, height);
            l_price.setForeground(Color.red);
            
            x3=x2+width+5;
            l_gst=new JLabel("Item GST");
            l_gst.setBounds(x3, y, width, height);
            l_gst.setForeground(Color.red);
            
            x4=x3+width+5;
            l_stock=new JLabel("Item Stock");
            l_stock.setBounds(x4, y, width, height);
            l_stock.setForeground(Color.red);
            
            y=y+height+5;
            c_item=new JComboBox<>();
            c_item.setBounds(x, y, cwidth, height);
            c_item.setBackground(Color.white);
            
            t_name=new JTextField();
            t_name.setBounds(x1, y, width, height);
            
            t_price=new JTextField();
            t_price.setBounds(x2, y, width, height);
            
            t_gst=new JTextField();
            t_gst.setBounds(x3, y, width, height);
            
            t_stock=new JTextField();
            t_stock.setBounds(x4, y, width, height);
            
            x5=x4+width+5;
            b_update=new JButton("Update");
            b_update.setBounds(x5, y, width+20, height);
            b_update.setBackground(Color.red);
            b_update.setForeground(Color.white);
            
            y=y+height+50;
            l_add_stock=new JLabel("Add Item Stock");
            l_add_stock.setBounds(x+200, y, width+250, height);
            l_add_stock.setForeground(Color.BLUE);
            l_add_stock.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
            
            y=y+height+5;
            c_add_stock=new JComboBox<>();
            c_add_stock.setBackground(Color.white);
            c_add_stock.setBounds(x+dif, y, cwidth, height);
            
            t_add_stock=new JTextField();
            t_add_stock.setBounds(x1+dif, y, width, height);
            
            b_add_stock=new JButton("Add Stock");
            b_add_stock.setBounds(x2+dif, y, width+30, height);
            b_add_stock.setBackground(Color.BLUE);
            b_add_stock.setForeground(Color.white);
            
            y=y+height+50;
            l_retail_stock=new JLabel("Retail Stock");
            l_retail_stock.setForeground(Color.GRAY);
            l_retail_stock.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
            l_retail_stock.setBounds(x+200, y, width+250, height);
            
            y=y+height+5;
            c_retail_stock=new JComboBox<>();
            c_retail_stock.setBackground(Color.white);
            c_retail_stock.setBounds(x+dif, y,cwidth, height);
            
            t_retail_stock=new JTextField();
            t_retail_stock.setBounds(x1+dif, y, width, height);
            
            b_retail_stock=new JButton("Update Stock");
            b_retail_stock.setBackground(Color.GRAY);
            b_retail_stock.setForeground(Color.white);
            b_retail_stock.setBounds(x2+dif, y, width+50, height);
            
            int x6=x5+width+30;
            t_item_view=new JTable(buildTableModel(rs));
            t_item_view.getColumn("Iname").setMinWidth(200);
            t_item_view.setEnabled(false);
            sbills = new JScrollPane(t_item_view);
            System.out.println(columnCount);
            t_item_view.setBackground(Color.white);
            t_item_view.setForeground(Color.gray);
            sbills.setBounds(x6, y1, columnCount * 100-50, 400);
            
            
            
            
            itemCombo();
            f_update_item.add(sbills);
            f_update_item.add(l_update_item);
            f_update_item.add(l_name);
            f_update_item.add(l_price);
            f_update_item.add(l_gst);
            f_update_item.add(l_stock);
            f_update_item.add(c_item);
            f_update_item.add(t_name);
            f_update_item.add(t_price);
            f_update_item.add(t_gst);
            f_update_item.add(t_stock);
            f_update_item.add(b_update);
            f_update_item.add(l_add_stock);
            f_update_item.add(c_add_stock);
            f_update_item.add(t_add_stock);
            f_update_item.add(b_add_stock);
            f_update_item.add(l_retail_stock);
            f_update_item.add(c_retail_stock);
            f_update_item.add(t_retail_stock);
            f_update_item.add(b_retail_stock);
            f_update_item.setLayout(null);
            f_update_item.setVisible(true);
            
            b_update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        
                        updateItemDetail(c_item, t_name, t_price, t_gst, t_stock);
                        JOptionPane.showMessageDialog(f_update_item,"Update successfully");
                        t_name.setText("");
                        t_price.setText("");
                        t_gst.setText("");
                        t_stock.setText("");
                        
                                                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (SQLException ex) {
                        Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            b_add_stock.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateStock(c_add_stock, t_add_stock);
                     JOptionPane.showMessageDialog(f_update_item,"Added successfully");
                    t_add_stock.setText("");
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            b_retail_stock.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    retailStock(c_retail_stock, t_retail_stock);
                     JOptionPane.showMessageDialog(f_update_item,"Update successfully");
                    t_retail_stock.setText("");
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(f_update_item,"please start server");
            //Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void updateItemDetail(JComboBox c_item,JTextField name,JTextField price,JTextField gst,JTextField stock) throws SQLException
    {
        String item=c_item.getSelectedItem().toString();
        String query;
        PreparedStatement p;
        
        if(!price.getText().equals(""))
        {
            Float price_val=Float.parseFloat(price.getText());
            query="update items set IPrice="+price_val+"where Iname='"+item+"'";
            p=con.prepareStatement(query);
            p.execute();
            System.out.println("price updated success fully");
        }
        if(!gst.getText().equals(""))
        {
            Float gst_val=Float.parseFloat(gst.getText());
            query="update items set Igst="+gst_val+"where Iname='"+item+"'";
            p=con.prepareStatement(query);
            p.execute();
            System.out.println("gst updated success fully");
        }
        if(!stock.getText().equals(""))
        {
            int stock_val=Integer.parseInt(stock.getText());
            System.out.println(stock_val);
            query="update items set stock= '"+stock_val+"' where Iname='"+item+"'";
            p=con.prepareStatement(query);
            p.execute();
            System.out.println("stock updated success fully");
        }
        if(!name.getText().equals(""))
        {            
            query="update items set Iname='"+name.getText()+"'where Iname='"+item+"'";
            p=con.prepareStatement(query);
            System.out.println("name updated success fully");
            p.execute();
            System.out.println("name updated success fully");
            this.c_item.removeItem(item);
            this.c_add_stock.removeItem(item);
            this.c_retail_stock.removeItem(item);
            this.c_item.addItem(name.getText());
            this.c_add_stock.addItem(name.getText());
            this.c_retail_stock.addItem(name.getText());
        }
        
         setTableData();
       
    }
    public void updateStock(JComboBox c_add_stock,JTextField stock)
    {
        try {
            String item=c_add_stock.getSelectedItem().toString();
            String query;
            PreparedStatement p;
            int stock_val=Integer.parseInt(stock.getText());
            System.out.println(stock_val);
            query="update items set stock=stock+ '"+stock_val+"' where Iname='"+item+"'";
            p=con.prepareStatement(query);
            p.execute();
            System.out.println("stock updated success fully");
            setTableData();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void retailStock(JComboBox c_add_stock,JTextField stock)
    {
        try {
            String item=c_add_stock.getSelectedItem().toString();
            String query;
            PreparedStatement p;
            int stock_val=Integer.parseInt(stock.getText());
            System.out.println(stock_val);
            query="update items set stock=stock- '"+stock_val+"' where Iname='"+item+"'";
            p=con.prepareStatement(query);
            p.execute();
            System.out.println("stock updated success fully");
            setTableData();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void itemCombo()
    {
        
        try {
            
            
            PreparedStatement p = con.prepareStatement("select * from items");
            ResultSet r = p.executeQuery();
            //c_item[i].addItem(" ");
            while (r.next()) {
                System.out.println(r.getString("Iname"));
                //items[i]=r.getString("Iname");
                c_item.addItem(r.getString("Iname"));
                c_add_stock.addItem(r.getString("Iname"));
                c_retail_stock.addItem(r.getString("Iname"));
                //k++;
                
                }
           
            } catch (SQLException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setTableData()
    {
        f_update_item.remove(sbills);
        try {
            String query="SELECT Iname as Name,Iprice as Price,Igst as GST,stock FROM `items`";
            PreparedStatement p=con.prepareStatement(query);
            ResultSet rs=p.executeQuery();
            
            int x6=x5+width+30;
            t_item_view=new JTable(buildTableModel(rs));
            t_item_view.getColumn("Iname").setMinWidth(200);
            sbills = new JScrollPane(t_item_view);
            System.out.println(columnCount);
            t_item_view.setBackground(Color.white);
            t_item_view.setForeground(Color.gray);
            sbills.setBounds(x6, y1, columnCount * 100-50, 400);
            f_update_item.add(sbills);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
   

