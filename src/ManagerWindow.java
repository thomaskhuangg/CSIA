import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class ManagerWindow extends JPanel
        implements ActionListener {

    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;

    public ManagerWindow() {
        super(new BorderLayout());

        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();
        fc.setDialogTitle("File Manager");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
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
                    log.append("Saving: " + file.getName() + "." + "\n");
                } catch (IllegalArgumentException iae) {
                    System.out.println("File not found");
                }
            }
        } else if (e.getSource() == saveButton)

        {
            int returnVal = fc.showSaveDialog(ManagerWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Saving: " + file.getName() + "." + "\n");
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
