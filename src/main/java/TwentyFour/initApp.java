package TwentyFour;

import java.awt.*;
import javax.swing.*;

public class initApp {

	private JFrame frame;
	private BottomPanel bottom;
	private MiddlePanel middle;
	private LeftPanel left;
	private RightPanel right;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					initApp window = new initApp();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public initApp() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout(20,20);
		frame.setLayout(new BorderLayout());

		bottom = new BottomPanel();
		middle = new MiddlePanel();
		middle.topAddCard();
		middle.topAddHideCard();
		middle.bottomAddCard();
		middle.top.cardPanel.cardContainers.get(middle.top.cardPanel.cardContainers.size()-1).turnFace();
		middle.bottomAddHideCard();
		left = new LeftPanel();
		right = new RightPanel();
		frame.add(bottom, BorderLayout.SOUTH);
		frame.add(left, BorderLayout.WEST);
		frame.add(right, BorderLayout.EAST);
		frame.add(middle, BorderLayout.CENTER);

	}

}




