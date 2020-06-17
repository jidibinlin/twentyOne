package TwentyFour;
import javax.swing.*;
import java.awt.*;

class BottomPanel extends JPanel {
	public typeButton stand;
	public typeButton hit;

    public BottomPanel() {

		stand = new typeButton("结束回合");
		hit = new typeButton("要牌");
		this.setLayout(new GridLayout(1,6));
		this.add(stand);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(hit);

	}

}