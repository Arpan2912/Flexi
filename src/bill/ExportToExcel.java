/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;

/**
 *
 * @author Thanx
 */
public class ExportToExcel {

    /* public void exportToExcel()
    {
        try {
    Class.forName(Db.driver_name);
    Connection con = DriverManager.getConnection(Db.con_string,Db.user_name,Db.password);
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("Select * from bill");
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("lawix10");
    HSSFRow rowhead = sheet.createRow((short) 0);
    rowhead.createCell((short) 0).setCellValue("CellHeadName1");
    rowhead.createCell((short) 1).setCellValue("CellHeadName2");
    rowhead.createCell((short) 2).setCellValue("CellHeadName3");
    int i = 1;
    while (rs.next()){
        HSSFRow row = sheet.createRow((short) i);
        row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("column1")));
        row.createCell((short) 1).setCellValue(rs.getString("column2"));
        row.createCell((short) 2).setCellValue(rs.getString("column3"));
        i++;
    }
    String yemi = "g:/test.xls";
    FileOutputStream fileOut = new FileOutputStream(yemi);
    workbook.write(fileOut);
    fileOut.close();
    } catch (ClassNotFoundException e1) {
       e1.printStackTrace();
    } catch (SQLException e1) {
        e1.printStackTrace();
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    }*/
   /* public static void main(String args[]){
        try {
            String query = "SELECT bill.Date,bill.Bid, bill.name, bill.Address,bill.phone,items.Iname,items.Iprice,purchase.discount,purchase.base_price,items.Icgst,items.Isgst ,purchase.qty, purchase.total AS \"Total\"\n"
            + "FROM items\n"
            + "INNER JOIN purchase ON purchase.Iid = items.Iid\n"
            + "INNER JOIN bill ON purchase.Bid = bill.Bid ";
            Class.forName(Db.driver_name);
            Connection con = DriverManager.getConnection(Db.con_string,Db.user_name,Db.password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ExportToExcel ete=new ExportToExcel();
           HSSFWorkbook sheet= ete.dump(rs);
            writeExcel(sheet);
            //System.out.println(sheet.toString());
           //ete.dumpData(rs,sheet, 0);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExportToExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExportToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public HSSFWorkbook dump(ResultSet resultSet) throws SQLException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFSheet sheet = workbook.createSheet("sheet");
        HSSFRow titleRow = sheet.createRow(0);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int colIndex = 0; colIndex < columnCount; colIndex++) {
            String title = metaData.getColumnLabel(colIndex + 1);
            HSSFCell cell = HSSFCellUtil.createCell(titleRow, colIndex, title);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFont(boldFont);
            cell.setCellStyle(style);
        }
        dumpData(resultSet, sheet, columnCount);
        return workbook;
    }

    private void dumpData(ResultSet resultSet, HSSFSheet sheet, int columnCount) throws SQLException {
        int currentRow = 1;
        
        resultSet.beforeFirst();
        while (resultSet.next()) {
            HSSFRow row = sheet.createRow(currentRow++);
            for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                Object value = resultSet.getObject(colIndex + 1);
                final HSSFCell cell = row.createCell(colIndex);
                if (value == null) {
                    cell.setCellValue("");
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                }
            }
        }
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    public static void writeExcel(HSSFWorkbook l_workBook_out,String path) {
        
        
  String l_str_file_out = path+"/results.xls"; //Give the location suitable to your requirement
  FileOutputStream fileOut;
  try {
   fileOut = new FileOutputStream(l_str_file_out);
   l_workBook_out.write(fileOut);
   fileOut.close();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
    
    
}
