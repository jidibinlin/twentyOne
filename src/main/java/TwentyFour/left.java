package TwentyFour;
import javax.swing.*;
import java.awt.*;

class LeftPanel extends JPanel{

	public LeftPanel() {
		this.setLayout(new BorderLayout());
		this.add(new typeButton("卡组"),BorderLayout.CENTER);
	}

}