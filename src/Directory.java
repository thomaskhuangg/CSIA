import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Directory extends JFrame
        implements ActionListener {

    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem aboutManager, aboutFill;
    private JButton manager;
    private JButton fill;


    public Directory() {

        super("Directory");

        panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(80, 10, 50, 10));

        menuBar = new JMenuBar();
        menu = new JMenu("About");
        aboutManager = new JMenuItem("About the File Manager...");
        aboutManager.addActionListener(this);
        aboutManager.setActionCommand("managerHelp");
        aboutFill = new JMenuItem("About the AutoFill...");
        aboutFill.addActionListener(this);
        aboutFill.setActionCommand("fillHelp");
        menu.add(aboutManager);
        menu.add(aboutFill);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        manager = new JButton("File Manager");
        manager.addActionListener(this);
        manager.setActionCommand("manager");
        manager.setPreferredSize(new Dimension(250, 150));

        fill = new JButton("AutoFill");
        fill.addActionListener(this);
        fill.setActionCommand("fill");
        fill.setPreferredSize(new Dimension(250, 150));

        panel.add(manager);
        panel.add(Box.createHorizontalStrut(25));
        panel.add(fill);
        add(panel);
        revalidate();
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manager) {
            dispose();
            ManagerWindow.createAndShowGUI();
        } else if (e.getSource() == fill) {
            dispose();
        } else if (e.getSource() == aboutManager) {
            About.helpManager();
        } else if (e.getSource() == aboutFill) {
            About.helpFill();
        }

    }

    public static void main(String[] args) {

        String windows = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            System.out.println(windows + " PLAF not installed");
        }

        Directory window = new Directory();
        window.setBounds(500, 200, 600, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

}



