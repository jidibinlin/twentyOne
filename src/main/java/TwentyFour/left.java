package TwentyFour;
import javax.swing.*;
import java.awt.*;

class LeftPanel extends JPanel{
	typeButton chip;

	public LeftPanel() {
		this.setLayout(new BorderLayout());
		chip = new typeButton("400");
		this.add(chip,BorderLayout.CENTER);
	}
}
