import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.Date;

public class FileTable extends AbstractTableModel {
    private File[] files;
    private String[] columns = {"Name", "Path", "Last Modified", "Size"};

    FileTable() {
        this(new File[0]);
    }

    FileTable(File[] file) {
        this.files = files;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return file.getName();
            case 1:
                return file.getAbsolutePath();
            case 2:
                return file.length();
            case 3:
                return file.lastModified();
            default:
                System.err.println("Error");
        }
        return "";
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
            case 1:
            case 2:
                return Long.class;
            case 3:
                return Date.class;
        }
        return String.class;
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}
