package TwentyFour;
import javax.swing.*;
import java.awt.*;

class BottomPanel extends JPanel {

    public BottomPanel() {
		this.setLayout(new GridLayout(1,6));
		this.add(new typeButton("Stand"));
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new typeButton("Double"));
		this.add(new typeButton("HIT"));

	}

}