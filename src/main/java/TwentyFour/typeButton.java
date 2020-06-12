package TwentyFour;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class typeButton extends JButton{

    public typeButton(String text) {
        super(text);
        Dimension preferredSize = new Dimension(100, 30);
        this.setPreferredSize(preferredSize);
        this.setEnabled(false);;
    }
    
}