package TwentyFour;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.*;

public class WinDialog extends JDialog {
    public static String status;

    public WinDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        JLabel lb = new JLabel(status);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(lb,BorderLayout.CENTER);
        this.add(container);
        this.setBounds(120,120,200,200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}