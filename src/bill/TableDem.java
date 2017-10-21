/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Thanx
 */
public class TableDem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame=new JFrame("Print");
        JPanel p=new JPanel();
        p.setBounds(0,0,100,100);
        JLabel jl=new JLabel("Hello");
        jl.setBounds(70,70,50,30);
         JLabel jl1=new JLabel("World");
        jl.setBounds(70,90,50,30);
        /*String data[][]={{"Arpan kk.vnvkkhihnvk","123"},{"Sanyam","2456"} };
        String column[]={"name","number"};
        JTable jTable=new JTable(data,column);
        jTable.setBounds(70,70,200,200);
        //JScrollPane sp=new JScrollPane(jTable);  
        jTable.setRowHeight(30);
        
       // sp.setBounds(70,70,200,200);
        //frame.add(sp);      
        frame.add(jTable);*/
       p.add(jl);
       p.add(jl1);
       p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
       
       p.setVisible(true);
       frame.add(p);
        frame.setSize(300,400);    
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
}
