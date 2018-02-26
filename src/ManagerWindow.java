import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;

public class ManagerWindow extends JPanel
        implements ActionListener {

    private JPanel buttonPanel, fileDetails, fileLabels, fileValues;
    private JButton openButton, saveButton;
    JTextArea log;
    private JTextField path;
    private JFileChooser fc;
    private JLabel fileName, date, size;

    public ManagerWindow() {

        super(new BorderLayout());
        fc = new JFileChooser();
        fc.setDialogTitle("File Manager");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        buttonPanel = new JPanel();
        log = new JTextArea(5, 20);
        log.setMargin(new Insets (5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fileDetails = new JPanel(new BorderLayout(10, 10));
        fileDetails.setBorder(new EmptyBorder(0, 6, 0, 6));

        fileLabels = new JPanel(new GridLayout(0, 1, 5, 5));
        fileDetails.add(fileLabels, BorderLayout.WEST);

        fileValues = new JPanel(new GridLayout(0, 1, 5, 5));
        fileDetails.add(fileValues, BorderLayout.CENTER);

        fileLabels.add(new JLabel("File", JLabel.TRAILING));
        fileName = new JLabel();
        fileValues.add(fileName);
        fileLabels.add(new JLabel("Path", JLabel.TRAILING));
        path = new JTextField(5);
        path.setEditable(false);
        fileValues.add(path);
        fileLabels.add(new JLabel("Last Modified", JLabel.TRAILING));
        date = new JLabel();
        fileValues.add(date);
        fileLabels.add(new JLabel("Size", JLabel.TRAILING));
        size = new JLabel();
        fileValues.add(size);

        int count = fileLabels.getComponentCount();
        for(int i = 0; i < count; i++){
            fileLabels.getComponent(i).setEnabled(false);
        }

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(fileDetails, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(ManagerWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filePath = file.getAbsolutePath();

                Desktop desktop = Desktop.getDesktop();
                File dirToOpen = new File(filePath);
                try {
                    desktop.open(dirToOpen);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                } catch (IllegalArgumentException iae) {
                    System.out.println("File not found");
                }
            }
        } else if (e.getSource() == saveButton)
        {
            int returnVal = fc.showSaveDialog(ManagerWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
             File file = fc.getSelectedFile();

            }
        }
    }

    public static void createAndShowGUI() {

        String windows = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            System.out.println(windows + " PLAF not installed");
        }

        JFrame window = new JFrame("File Manager");
        window.setBounds(500, 200, 600, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new ManagerWindow());
        window.setVisible(true);
    }
}
