package TwentyFour;

import java.awt.*;
import java.io.IOException;

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
     * 程序启动流程是  绘制frame->创建各个面板->从服务器拉取初始的两张卡牌->向frame中添加各个面板->开始对局
	 * 							L.Initialize-> L.getInitCard-> L.startVs
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout(20, 20);
		frame.setLayout(new BorderLayout());
		Logic L = new Logic();
		L.Initialize();//初始化

		// PlayerPanel p1=new PlayerPanel();
		// PlayerPanel p2=new PlayerPanel();
		// bottom = new BottomPanel();
		// middle = new MiddlePanel(p1,p2);

		// System.out.println(p1.getSumPoint());
		// System.out.println(p2.getSumPoint());
		// left = new LeftPanel();
		// right = new RightPanel();
		frame.add(L.bottom, BorderLayout.SOUTH); //添加各个panel
		frame.add(L.left, BorderLayout.WEST);
		frame.add(L.right, BorderLayout.EAST);
		frame.add(L.middle, BorderLayout.CENTER);
		// frame.setVisible(true);
		try {
			L.startVs();//开始对决
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




