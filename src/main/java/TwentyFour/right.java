package TwentyFour;
import javax.swing.*;
import java.awt.*;

class RightPanel extends JPanel{
	public RightPanel() {
		this.setLayout(new BorderLayout());
		this.add(new typeButton("卡组"),BorderLayout.CENTER);
	}

}