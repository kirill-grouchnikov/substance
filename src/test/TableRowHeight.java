package test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.MagellanSkin;

public class TableRowHeight extends JFrame {

    public TableRowHeight() {
        TableModel model = new TableModel() {
            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            }
            
            @Override
            public void removeTableModelListener(TableModelListener l) {
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return rowIndex + ":" + columnIndex;
            }
            
            @Override
            public int getRowCount() {
                return 10;
            }
            
            @Override
            public String getColumnName(int columnIndex) {
                return "" + columnIndex;
            }
            
            @Override
            public int getColumnCount() {
                return 10;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
            
            @Override
            public void addTableModelListener(TableModelListener l) {
            }
        };
        JTable table = new JTable(model);
        this.setLayout(new BorderLayout());
        this.add(table, BorderLayout.CENTER);
        table.setRowHeight(60);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SubstanceLookAndFeel.setSkin(new MagellanSkin());
            new TableRowHeight().setVisible(true);
        });
    }

}
