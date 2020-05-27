package TwentyFour;
import javax.swing.*;
import java.awt.*;

class BottomPanel extends JPanel {

    public BottomPanel() {
		this.setLayout(new GridLayout(1,6));
		this.add(new defaultButton("Stand"));
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new defaultButton("Double"));
		this.add(new defaultButton("HIT"));

	}

}