package TwentyFour;
import javax.swing.*;
import java.awt.*;

class RightPanel extends JPanel{
	private ImageIcon i=null;
	public RightPanel() {
		i = new ImageIcon("pukeImage/back.jpg");
		i.setImage(i.getImage().getScaledInstance(105, 150, Image.SCALE_DEFAULT));
		this.setLayout(new BorderLayout());
		JLabel l = new JLabel();
		l.setIcon(i);
		this.add(l,BorderLayout.CENTER);
	}

}