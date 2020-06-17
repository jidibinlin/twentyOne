package TwentyFour;

import java.io.IOException;

public class Logic {
    Server server = new Server();
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
    }

    public void startVs() {

    }

    public void getInitCard() throws IOException {
        String initDate= server.reader.readLine();
        InitVs ini = DateParser.parseInitVS(initDate);
        Card client1_1 = cards.getCard(ini.getClient1().getCardName1());
        Card client1_2 = cards.getCard(ini.getClient1().getCardName2());
        Card client2_1 = cards.getCard(ini.getClient2().getCardName1());
        Card client2_2 = cards.getCard(ini.getClient2().getCardName2());

        p1.addCard(client1_1.cardName,client1_1.cardPoint);
        p1.addCard(client1_2.cardName,client1_2.cardPoint);
        p2.addCard(client2_1.cardName,client2_1.cardPoint);
        p2.addCard(client2_2.cardName,client2_2.cardPoint);

    }
}