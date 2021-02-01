/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.tablecarton;




import appjugador.Sistema;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Santiago
 */
public class CartonCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                 
        try {
            JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            MatteBorder border;
            this.setHorizontalAlignment(JLabel.CENTER);
            if(isSelected){
                border = new MatteBorder(2, 2, 2,2, Color.blue);
                this.setFont(new Font("Arial",Font.BOLD,25));
            }else{
                border = new MatteBorder(1, 1, 1, 1, Color.black);
                this.setFont(new Font("Arial",Font.PLAIN,15));
                
            }
            this.setBorder(border);            
            
            this.setText(String.valueOf(value));
            ArrayList<Integer> bolillas = Sistema.getFachada().getBolillasSorteadas();
            if(bolillas != null || !bolillas.isEmpty()) {
                for (Integer b : bolillas) {             
                    String celda = String.valueOf(value);
                    String bol = String.valueOf(b);
                    if(bol.equals(celda)){                        
                        border = new MatteBorder(2, 2, 2,2, Color.red);
                        cell.setBorder(border);                        
                    }
                }
            }
            this.setOpaque(true);
            return this;        
        } catch (RemoteException ex) {
            Logger.getLogger(CartonCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
        


