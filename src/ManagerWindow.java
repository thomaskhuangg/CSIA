import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static javax.swing.SwingConstants.WEST;

public class ManagerWindow extends JPanel
        implements ActionListener {

    private JPanel buttonPanel, fileDetails, fileLabels, fileValues;
    private JButton backButton, openButton, saveButton, deleteButton;
    private JList list;
    private JTextField nameField, pathField, dateField, sizeField;
    private JFileChooser fc;
    private Paths[] paths;
    private String textName = "FileNames.txt";

    public ManagerWindow() {

        super(new BorderLayout());
        paths = new Paths[0];
        loadPaths();
        fc = new JFileChooser();
        fc.setDialogTitle("File Manager");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        buttonPanel = new JPanel();

        list = new JList(paths);
        list.setVisibleRowCount(-1);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selected = list.getSelectedValue().toString();
                    File dirToOpen = new File(selected);

                    Date d = new Date(dirToOpen.lastModified());

                    nameField.setText(dirToOpen.getName().toString());
                    pathField.setText(dirToOpen.getAbsolutePath());
                    dateField.setText(d.toString());
                    sizeField.setText(Long.toString(dirToOpen.length()) + " " + "bytes");
                }
                if (e.getClickCount() == 2) {
                    String selected = list.getSelectedValue().toString();
                    Desktop desktop = Desktop.getDesktop();
                    File dirToOpen = new File(selected);
                    try {
                        desktop.open(dirToOpen);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (IllegalArgumentException iae) {
                        System.out.println("File not found");
                    }
                }
            }
        });
        JScrollPane logScrollPane = new JScrollPane(list);


        fileDetails = new JPanel(new BorderLayout(10, 10));
        fileDetails.setBorder(new EmptyBorder(0, 6, 0, 6));

        fileLabels = new JPanel(new GridLayout(0, 1, 5, 5));
        fileDetails.add(fileLabels, BorderLayout.WEST);

        fileValues = new JPanel(new GridLayout(0, 1, 5, 5));
        fileDetails.add(fileValues, BorderLayout.CENTER);

        fileLabels.add(new JLabel("File", JLabel.TRAILING));
        nameField = new JTextField(5);
        nameField.setEditable(false);
        nameField.setBorder(null);
        fileValues.add(nameField);
        fileLabels.add(new JLabel("Path", JLabel.TRAILING));
        pathField = new JTextField(5);
        pathField.setEditable(false);
        fileValues.add(pathField);
        fileLabels.add(new JLabel("Last Modified", JLabel.TRAILING));
        dateField = new JTextField(5);
        dateField.setEditable(false);
        dateField.setBorder(null);
        fileValues.add(dateField);
        fileLabels.add(new JLabel("Size", JLabel.TRAILING));
        sizeField = new JTextField(5);
        sizeField.setEditable(false);
        sizeField.setBorder(null);
        fileValues.add(sizeField);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);
        deleteButton = new JButton("Delete a File...");
        deleteButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(fileDetails, BorderLayout.PAGE_END);
    }

    public void addPaths(Paths paths1) {

        Paths[] temp = new Paths[paths.length + 1];

        for (int i = 0; i < paths.length; i++) {

            temp[i] = paths[i];

        }

        temp[temp.length - 1] = paths1;
        paths = temp;
    }

    public boolean deletePaths(int index) {

        if (index >= 0 && index < paths.length) {

            Paths[] temp = new Paths[paths.length - 1];

            for (int i = 0; i < paths.length; i++) {

                if (i < index) {

                    temp[i] = paths[i];

                } else if (i > index) {

                    temp[i - 1] = paths[i];
                }
            }

            paths = temp;
            return true;

        } else {
            return false;
        }
    }


    public void loadPaths() {

        try {

            File f = new File(textName);
            Scanner fs = new Scanner(f);

            while (fs.hasNextLine()) {
                String filePaths = fs.nextLine();
                addPaths(new Paths(filePaths));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + textName);
        }

    }

    public void savePaths() {
        try {
            File f = new File(textName);
            PrintWriter pw = new PrintWriter(f);
            for (Paths p : paths) {
                pw.println(p);
            }

            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + textName);
        }
    }

    public void dispose() {
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();

    }

    public String toString() {

        String toStr = "";

        for (int i = 0; i < paths.length; i++) {

            if (i <= paths.length - 1) {

                toStr += paths[i] + "\n";
            }
        }
        return toStr;
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
                } catch (IllegalArgumentException iae) {
                    System.out.println("File not found");
                }
                CreateAndShowGUI();
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
                Paths path = new Paths(filePath);
                addPaths(path);
                savePaths();
            } else if (e.getSource() == deleteButton) {

            }
        } else if (e.getSource() == backButton) {
            dispose();
            String[] arguments = new String[]{"123"};
            Directory.main(arguments);
            return;
        }
        CreateAndShowGUI();
    }


    public static void CreateAndShowGUI() {

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

