/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramserver;

import ProgramLab.Police;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tuandung
 */
public class PoliceTableModel extends AbstractTableModel {

    List<Police> data;
    String[] colNames = {"name", "dob", "current_position", "speed", "atk", "color"};

    public PoliceTableModel(ConcurrentSkipListSet<Police> csSet) {
        this.data = new ArrayList<>();
        Iterator<Police> iterator = csSet.iterator();
        while (iterator.hasNext()) {
            this.data.add(iterator.next());
        }
    }

    public PoliceTableModel(List<Police> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    public void add(Police police) {
        data.add(police);
        fireTableDataChanged();

    }

    public List<Police> getALl() {
        return data;
    }

    public void delete(int i) {
        data.remove(i);
    }

    public Police get(int i) {
        return data.get(i);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Police police = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return police.getName();
            case 1:
                return police.getDob().toString();
            case 2:
                return police.getCurrent_position();
            case 3:
                return police.getSpeed();
            case 4:
                return police.getAtk();
            case 5:
                return "(" + police.getColor().getRed() + "," + police.getColor().getGreen() + "," + police.getColor().getBlue() + ")";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

}
