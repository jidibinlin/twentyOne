package TwentyFour;

import java.awt.*;
import javax.swing.*;

public class initApp {

	private JFrame frame;
	// private BottomPanel bottom;
	// private MiddlePanel middle;
	// private LeftPanel left;
	// private RightPanel right;

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
		Logic L = new Logic();
		L.Initialize();

		// PlayerPanel p1=new PlayerPanel();
		// PlayerPanel p2=new PlayerPanel();
		// bottom = new BottomPanel();
		// middle = new MiddlePanel(p1,p2);

		// System.out.println(p1.getSumPoint());
		// System.out.println(p2.getSumPoint());
		// left = new LeftPanel();
		// right = new RightPanel();
		frame.add(L.bottom, BorderLayout.SOUTH);
		frame.add(L.left, BorderLayout.WEST);
		frame.add(L.right, BorderLayout.EAST);
		frame.add(L.middle, BorderLayout.CENTER);
	}

}




