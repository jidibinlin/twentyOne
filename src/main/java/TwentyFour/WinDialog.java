package TwentyFour;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.*;

public class WinDialog extends JDialog {
    public static String status;

    public WinDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.setLayout(new BorderLayout());
        this.setBounds(120,120,200,200);
        JLabel lb = new JLabel(status);
        this.add(lb,BorderLayout.CENTER);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}