package TwentyFour;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.*;

public class WinDialog extends JDialog {
    public static String status;

    public WinDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        BorderLayout l = new BorderLayout(20,20);
        this.setLayout(l);
        JLabel lb = new JLabel(status);
        this.add(lb,BorderLayout.CENTER);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}