import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoFillWindow extends JPanel
        implements ActionListener {

    private JPanel namePanel, toolPanel;
    private JLabel name;
    private JButton create, delete, rename;
    private JTextArea log;

    public AutoFillWindow() {

        super(new BorderLayout());

        namePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        Font font = new Font("Monospaced", Font.BOLD, 18);
        namePanel.setFont(font);
        name = new JLabel("Name");
        namePanel.add(Box.createHorizontalStrut(20));
        namePanel.add(name);

        toolPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        create = new JButton("Create");
        create.addActionListener(this);
        delete = new JButton("Delete");
        delete.addActionListener(this);
        rename = new JButton("Rename");
        rename.addActionListener(this);
        toolPanel.add(create);
        toolPanel.add(delete);
        toolPanel.add(rename);

        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        add(namePanel, BorderLayout.PAGE_START);
        add(toolPanel, BorderLayout.PAGE_END);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void dispose() {
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            dispose();
            CreateTemplate.CreateAndShowGUI();
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
        window.add(new AutoFillWindow());
        window.setVisible(true);
    }
}


