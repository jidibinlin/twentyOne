package TwentyFour;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.*;

public class WinDialog extends JDialog {

    public WinDialog(String status) {
        BorderLayout l = new BorderLayout(20,20);
        this.setLayout(l);
        JLabel lb = new JLabel(status);
        this.add(lb,BorderLayout.CENTER);
        this.setModal(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}