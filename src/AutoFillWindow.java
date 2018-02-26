import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoFillWindow extends JPanel
        implements ActionListener {

    public AutoFillWindow() {

        super(new BorderLayout());


    }

    public void actionPerformed(ActionEvent e) {

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


