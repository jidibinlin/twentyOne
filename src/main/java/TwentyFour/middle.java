package TwentyFour;

import javax.swing.*;
import java.awt.*;

class MiddlePanel extends JPanel{

	public MiddlePanel() {
		this.setLayout(new GridLayout(2,2));
		this.add(new defaultButton("用户一点数"));
		this.add(new defaultButton("用户一卡牌"));
		this.add(new defaultButton("用户二点数"));
		this.add(new defaultButton("用户二卡牌"));
	}

}