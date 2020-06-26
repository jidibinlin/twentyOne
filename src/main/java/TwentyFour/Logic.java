package TwentyFour;

import java.io.IOException;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class Logic {
    Server server = null;
    String first;
    public BottomPanel bottom;
    public MiddlePanel middle;
    public LeftPanel left = new LeftPanel();
    public RightPanel right = new RightPanel();

    private PlayerPanel p1;
    private PlayerPanel p2;
    private Cards cards;
    private int status;
    private JFrame frame = null;
    private Thread th = null;

    private Thread t1=null;
    public Logic(JFrame frame) {
        this.frame = frame;

    }

    public void Initialize() {
        server = new Server();
        cards = new Cards();

        p1 = new PlayerPanel();// 己方界面
        p2 = new PlayerPanel();// 对方界面
        bottom = new BottomPanel();// 底部按钮界面
        middle = new MiddlePanel(p1, p2);// 中间卡牌界面 承载p1 p2
        // left = new LeftPanel();// 左部筹码界面
        // right = new RightPanel();// 右部卡组界面
        frame.add(bottom, BorderLayout.SOUTH); // 添加各个panel
        frame.add(left, BorderLayout.WEST);
        frame.add(right, BorderLayout.EAST);
        frame.add(middle, BorderLayout.CENTER);
        frame.setVisible(true);

        server.connect();// 与服务器建立链接
        try {
            getInitCard();// 向服务器拉取初始卡牌
            frame.setVisible(true);
            startVs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void changeChip(int status){
        int orig =Integer.parseInt(left.chip.getText());
        if(status == 0)
            return;
        else if(status == 1)
            left.chip.setText(""+(orig+10));
        else if(status == 2)
            left.chip.setText(""+(orig-10));

    }

    public void startVs() throws IOException {
        ActionListener hit = new ActionListener() { // 创建要卡按钮监听事件
            public void actionPerformed(ActionEvent e) {
                String toSend = ClientDate.genRequestCard("requestCard", "client1");
                if (first.equals("client1")) { // 如果是先手的话就向服务器请求数据
                    try {
                        server.writer.write(toSend + "\n");
                        server.writer.flush();
                        System.out.println("向服务器发出要卡请求");
                        String cardDate = server.reader.readLine();
                        System.out.println("收到服务器的卡牌");
                        SendCard sendCard = DateParser.parseSendCard(cardDate);
                        Card card = cards.getCard(sendCard.getCardName());
                        p1.addCard(card);
                        System.out.println("添加卡牌");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    if (p1.getSumPoint() > 21) {
                        /**
                         * 如果卡牌点数大于21点则 p2赢 退出
                         */
                        status = 2;
                        bottom.hit.setEnabled(false);
                        bottom.stand.setEnabled(false);
                        changeChip(status);
                        gameCycle(status);
                        return;
                    }

                } else {
                    // 后手要卡的情况
                    try {
                        server.writer.write(toSend + "\n");
                        server.writer.flush();
                        System.out.println("向服务器发出要卡请求");
                        String cardDate = server.reader.readLine();
                        System.out.println("收到服务器的卡牌");
                        SendCard sendCard = DateParser.parseSendCard(cardDate);
                        Card card = cards.getCard(sendCard.getCardName());
                        p1.addCard(card);
                        System.out.println("添加卡牌");

                    } catch (IOException e1) {
                        // TODO: handle exception
                        e1.printStackTrace();
                    }

                    if (p1.getSumPoint() > 21) {
                        status = 2;
                        p2.turnFace();
                        changeChip(status);
                        gameCycle(status);
                        return;
                    }
                }
            }
        };

        ActionListener stand = new ActionListener() { // 创建结束回合按钮监听事件
            public void actionPerformed(ActionEvent e) {
                Contact_Stand r = new Contact_Stand();
                th = new Thread(r);
                th.start();

            }
        };
        bottom.hit.addActionListener(hit); // 添加事件
        bottom.stand.addActionListener(stand); // 添加事件
        Contact contac = new Contact();
        t1 = new Thread(contac);
        t1.start();

    }

    class Contact_Stand implements Runnable {
        public void run() {
            try {
                if (first.equals("client1")) { // 先手停止要卡
                    String toSend = ClientDate.genRequestCard("stopRequestCard", "client1");
                    System.out.println("发送停止要拍指令" + toSend);
                    server.writer.write(toSend + "\n");
                    server.writer.flush();
                    String s1;
                    String dateType1;
                    bottom.hit.setEnabled(false);
                    bottom.stand.setEnabled(false);
                    do { // 获取p2发送的卡牌
                        s1 = server.reader.readLine();
                        dateType1 = DateParser.getDateType(s1);
                        if (dateType1.equals("sendCard")) {
                            SendCard tmp = DateParser.parseSendCard(s1);
                            Card card = cards.getCard(tmp.getCardName());
                            p2.addHideCard(card, true);
                        }
                        if (p2.getSumPoint() > 21) {
                            /**
                             * p1 Win 退出
                             */
                            status = 1;
                            p2.turnFace();
                            changeChip(status);
                            gameCycle(status);

                            return;
                        }

                    } while (!dateType1.equals("stopSend"));

                    if ((p1.getSumPoint()== 21) && (p2.getSumPoint() == 21)) {
                        /**
                         * 平局 游戏结束
                         */
                        status = 0;
                        p2.turnFace();
                        changeChip(status);
                        gameCycle(status);

                        return;
                    } else {
                        if (p1.getSumPoint() > p2.getSumPoint()) {
                            /**
                             * p1 win 退出
                             */
                            status = 1;
                            p2.turnFace();
                            changeChip(status);
                            gameCycle(status);

                            return;

                        } else {
                            /**
                             * p2 win 退出
                             */
                            status = 2;
                            p2.turnFace();
                            changeChip(status);
                            gameCycle(status);
                            return;
                        }
                    }

                } else {
                    // 后手停止要卡的情况
                    while (p1.getSumPoint() < 17) {
                        String toSend2 = ClientDate.genRequestCard("requestCard", "client1");
                        try {
                            server.writer.write(toSend2 + "\n");
                            server.writer.flush();
                            System.out.println("向服务器发出要卡请求 line203");
                            String cardDate = server.reader.readLine();
                            System.out.println("收到服务器的卡牌");
                            SendCard sendCard = DateParser.parseSendCard(cardDate);
                            Card card = cards.getCard(sendCard.getCardName());
                            p1.addCard(card);
                            System.out.println("添加卡牌");

                        } catch (IOException e1) {
                            // TODO: handle exception
                            e1.printStackTrace();
                        }

                    }
                    String toSend2 = ClientDate.genRequestCard("stopRequestCard", "client1");
                    System.out.println("发送停止要拍指令" + toSend2);
                    server.writer.write(toSend2 + "\n");
                    server.writer.flush();

                    if (p1.getSumPoint() > 21) {
                        /**
                         * p2 win
                         */
                        status = 2;
                        p2.turnFace();
                        changeChip(status);
                        gameCycle(status);
                        return;
                    } else {
                        if (p1.getSumPoint() == 21 && p2.getSumPoint() == 21) {
                            /**
                             * 平局
                             */
                            status = 0;
                            p2.turnFace();
                            changeChip(status);
                            gameCycle(status);

                            return;
                        } else {
                            if (p1.getSumPoint() > p2.getSumPoint()) {
                                /**
                                 * p1 win
                                 */
                                status = 1;
                                p2.turnFace();
                                changeChip(status);
                                gameCycle(status);
                                return;
                            } else {
                                /**
                                 * p2 win
                                 */
                                status = 2;
                                p2.turnFace();
                                changeChip(status);
                                gameCycle(status);
                                return;
                            }
                        }
                    }
                }

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

    class Contact implements Runnable {
        public void run() {
            String dateType = null; // 数据类型
            String s = null; // 从服务器中接受的数据
            if (first.equals("client1")) {// 先手时按钮可用
                bottom.hit.setEnabled(true);
                bottom.stand.setEnabled(true);
            } else {
                bottom.hit.setEnabled(false); // 后手时按钮不可用 并在接收完初始卡牌后开始监听服务器
                bottom.stand.setEnabled(false);
                do {

                    /**
                     * 程序会在这里卡住一直等待服务器的数据 程序启动流程是
                     * 绘制frame->创建各个面板->从服务器拉取初始的两张卡牌->向frame中添加各个面板->开始对局
                     */

                    try {
                        s = server.reader.readLine();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    dateType = DateParser.getDateType(s);
                    if (dateType.equals("sendCard")) {
                        SendCard tmp = DateParser.parseSendCard(s);
                        System.out.println("收到服务器卡牌");
                        Card card = cards.getCard(tmp.getCardName());
                        p2.addHideCard(card, true);
                        System.out.println("添加卡牌");
                    }

                    if (p2.getSumPoint() > 21) {
                        /**
                         * p1 win 退出
                         */
                        status = 1;
                        p2.turnFace();
                        changeChip(status);
                        gameCycle(status);

                        return;
                    }

                } while (!dateType.equals("stopSend"));

                bottom.hit.setEnabled(true); // 后手等先手操作完成后将按钮设置为可用
                bottom.stand.setEnabled(true);
            }

        }

    }

    public void getInitCard() throws IOException {
        String initDate = server.reader.readLine();

        System.out.println("初始卡牌数据接收完毕卡牌数据为" + initDate);
        InitVs ini = DateParser.parseInitVS(initDate);
        Card client1_1 = cards.getCard(ini.getClient1().getCardName1());
        Card client1_2 = cards.getCard(ini.getClient1().getCardName2());
        Card client2_1 = cards.getCard(ini.getClient2().getCardName1());
        Card client2_2 = cards.getCard(ini.getClient2().getCardName2());
        first = ini.getFirst();

        System.out.println("卡牌数据处理完毕正在请求GUI");

        p1.addCard(client1_1);
        p1.addCard(client1_2);
        System.out.println("p1卡牌添加完毕 "+client1_1.cardName +" "+client1_2.cardName);
        p2.addCard(client2_1);
        p2.addHideCard(client2_2, true);
        System.out.println("p2卡牌添加完毕 "+client2_1.cardName +" "+client2_2.cardName);
        System.out.println("游戏开局初始化完毕");
        System.out.println("先手是" + ini.getFirst());

    }

    public void gameCycle(int status) {
        String winner = "";
        switch (status) {
            case 0:
                winner = "平局";

                break;
            case 1:
                winner = "你赢了";

                break;
            case 2:
                winner = "你输了";
                break;

            default:
                break;
        }
        server.close();
        WinDialog.status = winner;
        WinDialog dia = new WinDialog(frame, "tips", true);
        dia.setVisible(true);
        frame.getContentPane().removeAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Initialize();
    }
}