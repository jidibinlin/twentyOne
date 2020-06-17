package TwentyFour;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

public class Logic {
    Server server = new Server();
    String first;
	public BottomPanel bottom;
	public MiddlePanel middle;
	public LeftPanel left;
    public RightPanel right;

    private PlayerPanel p1;
    private PlayerPanel p2;
    private Cards cards = new Cards();

    public void Initialize() {

        p1=new PlayerPanel();
        p2=new PlayerPanel();
        bottom = new BottomPanel();
        middle = new MiddlePanel(p1,p2);
        left = new LeftPanel();
        right = new RightPanel();

        server.connect();
        try {
            getInitCard();
            startVs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startVs() throws IOException {
        String dateType=null;
        String s=null;
        if(first.equals("client1")){
            bottom.hit.setEnabled(true);
            bottom.stand.setEnabled(true);
        }
        else{
            bottom.hit.setEnabled(false);
            bottom.stand.setEnabled(false);
            do{
                s = server.reader.readLine();
                dateType = DateParser.getDateType(s);
                if(dateType.equals("sendCard")){
                    SendCard tmp = DateParser.parseSendCard(s);
                    Card card = cards.getCard(tmp.getCardName());
                    p2.addHideCard(card, true);
                }

            }while(dateType.equals("stopSend"));

            bottom.hit.setEnabled(true);
            bottom.stand.setEnabled(true);


        }

        ActionListener hit = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String toSend = ClientDate.genRequestCard("requestCard", "client1");
                if(first.equals("client1")){
                    try {
                        server.writer.write(toSend + "\n");
                        server.writer.flush();
                        String cardDate = server.reader.readLine();
                        SendCard sendCard = DateParser.parseSendCard(cardDate);
                        Card card = cards.getCard(sendCard.getCardName());
                        p1.addCard(card);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                } 
            }
        };

        ActionListener stand = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String toSend = ClientDate.genRequestCard("stopRequestCard", "client1");

                try {
                    server.writer.write(toSend + "\n");
                    if(first.equals("client1")){
                        String s1;
                        String dateType1;
                        bottom.hit.setEnabled(false);
                        bottom.stand.setEnabled(false);
                        do{
                            s1 = server.reader.readLine();
                            dateType1 = DateParser.getDateType(s1);
                            if(dateType1.equals("sendCard")){
                                SendCard tmp = DateParser.parseSendCard(s1);
                                Card card = cards.getCard(tmp.getCardName());
                                p2.addHideCard(card, true);
                            }

                        }while(dateType1.equals("stopSend"));
                    }

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }



            }
        };
        bottom.hit.addActionListener(hit);
        bottom.stand.addActionListener(stand);

    }

    public void getInitCard() throws IOException {
        String initDate= server.reader.readLine();

        System.out.println("初始卡牌数据接收完毕卡牌数据为"+initDate);
        InitVs ini = DateParser.parseInitVS(initDate);
        Card client1_1 = cards.getCard(ini.getClient1().getCardName1());
        Card client1_2 = cards.getCard(ini.getClient1().getCardName2());
        Card client2_1 = cards.getCard(ini.getClient2().getCardName1());
        Card client2_2 = cards.getCard(ini.getClient2().getCardName2());
        first = ini.getFirst();

        System.out.println("卡牌数据处理完毕正在请求GUI");

        p1.addCard(client1_1);
        p1.addCard(client1_2);
        p2.addCard(client2_1);
        p2.addHideCard(client2_2,true);
        System.out.println("游戏开局初始化完毕");

    }
}