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

    private JPanel panel, buttonPanel, fileDetails, fileLabels, fileValues;
    private JButton openButton, saveButton;
    JTextArea log;
    private JTextField path;
    private JFileChooser fc;
    private JLabel fileName, date, size;
    private String name = "FileNames.txt";

    public ManagerWindow() {

        super(new BorderLayout());

        fc = new JFileChooser();
        fc.setDialogTitle("File Manager");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        buttonPanel = new JPanel();
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);


        try {
            FileReader reader = new FileReader("FileNames.txt");
            BufferedReader br = new BufferedReader(reader);
            log.read(br, null);
            br.close();
            log.requestFocus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        for (int i = 0; i < count; i++) {
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

    public void dispose() {
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openButton) {
            dispose();
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
                createAndShowGUI();
            }
        } else if (e.getSource() == saveButton) {
            dispose();
            int returnVal = fc.showSaveDialog(ManagerWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                File file = fc.getSelectedFile();
                String filePath = file.getAbsolutePath();

                try {
                    File file1 = new File(name);
                    PrintWriter pw = new PrintWriter(file1);
                    pw.write(filePath + "\n");
                    pw.flush();
                    pw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            createAndShowGUI();
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

