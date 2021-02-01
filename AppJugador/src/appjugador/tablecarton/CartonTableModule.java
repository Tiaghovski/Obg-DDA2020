/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.tablecarton;



import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Santiago
 */
public class CartonTableModule extends AbstractTableModel {
    Integer[][] cartones;

    public CartonTableModule(Integer[][] cartones) {
        this.cartones = cartones;
    } 

    @Override
    public int getRowCount() {
        return cartones.length;
    }

    @Override
    public int getColumnCount() {
        return cartones[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return cartones[rowIndex][columnIndex];
    }
    
}
