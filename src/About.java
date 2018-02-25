import javax.swing.*;

public class About {

    public static void helpManager() {

        JOptionPane.showMessageDialog(null,
                "This file manager aims to provide an easy way of  \n" +
                        "managing files in regard to maintaining them, \n" +
                        "making sure not to lose track of them, keep them in \n" +
                        "chronological order, and other things of the such.",
                "About the File Manager",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void helpFill() {

        JOptionPane.showMessageDialog(null,
                "This 'AutoFill' program works to be more efficient in regard \n" +
                        "to filling PDF's, surveys, etc. out with ease, rather than having to \n" +
                        "continually copy and paste, or even type it in, this program \n" +
                        "will allow one to make templates to automatically fill things out.",
                "About the AutoFill",
                JOptionPane.PLAIN_MESSAGE);
    }

}

