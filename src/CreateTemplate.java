import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTemplate extends JPanel
        implements ActionListener {

    private JPanel labelPanel, textPanel, donePanel;
    private JLabel label, textField;
    private JTextField field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18;
    private JButton done;

    public CreateTemplate() {

        super(new BorderLayout());

        labelPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        label = new JLabel("Label");
        textField = new JLabel("Text Field");
        labelPanel.add(label);
        labelPanel.add(Box.createHorizontalStrut(20));
        labelPanel.add(textField);

        textPanel = new JPanel(new GridLayout(8, 2));
        field1 = new JTextField("");
        field2 = new JTextField("");
        field3 = new JTextField("");
        field4 = new JTextField("");
        field5 = new JTextField("");
        field6 = new JTextField("");
        field7 = new JTextField("");
        field8 = new JTextField("");
        field9 = new JTextField("");
        field10 = new JTextField("");
        field11 = new JTextField("");
        field12 = new JTextField("");
        field13 = new JTextField("");
        field14 = new JTextField("");
        field15 = new JTextField("");
        field16 = new JTextField("");

        textPanel.add(field1);
        textPanel.add(field2);
        textPanel.add(field3);
        textPanel.add(field4);
        textPanel.add(field5);
        textPanel.add(field6);
        textPanel.add(field7);
        textPanel.add(field8);
        textPanel.add(field9);
        textPanel.add(field10);
        textPanel.add(field11);
        textPanel.add(field12);
        textPanel.add(field13);
        textPanel.add(field14);
        textPanel.add(field15);
        textPanel.add(field16);

        donePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        done = new JButton("Done");
        done.addActionListener(this);
        donePanel.add(done);

        add(labelPanel, BorderLayout.PAGE_START);
        add(textPanel, BorderLayout.CENTER);
        add(donePanel, BorderLayout.PAGE_END);
    }

    public void dispose() {
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == done) {
            dispose();
            AutoFillWindow.CreateAndShowGUI();
        }
    }

    public static void CreateAndShowGUI() {

        String windows = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            System.out.println(windows + " PLAF not installed");
        }

        JFrame window = new JFrame("AutoFill");
        window.setBounds(500, 200, 600, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new CreateTemplate());
        window.setVisible(true);
    }

}



