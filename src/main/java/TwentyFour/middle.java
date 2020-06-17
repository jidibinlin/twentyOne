package TwentyFour;

import javax.swing.*;
import java.awt.*;
// import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashSet;
// import java.util.HashSet;
// import java.util.Random;
import java.util.Random;

class MiddlePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public PlayerPanel p1 = null;
	public PlayerPanel p2=null;

	public MiddlePanel(PlayerPanel p1,PlayerPanel p2) {
		this.setLayout(new GridLayout(2, 1, 100, 100));
		// top=new PlayerPanel();
		// bottom=new PlayerPanel();
		this.p1=p1;
		this.p2=p2;
		this.add(p2);
		this.add(p1);
	}

	// public void p1AddCard(String cardName,int cardPoint){
	// 	p1.addCard(cardName, cardPoint);
	// }
	// public void p1AddHideCard(String cardName,int cardPoint, boolean backFace){
	// 	p1.addHideCard(cardName, cardPoint, backFace);
	// }
	// public void p2AddCard(String cardName,int cardPoint){
	// 	p2.addCard(cardName, cardPoint);
	// }
	// public void p2AddHideCard(String cardName,int cardPoint, boolean backFace){
	// 	p2.addHideCard(cardName, cardPoint, backFace);
	// }

}

class PlayerPanel extends JPanel {   //玩家面板

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private typeButton sumPointBut = new typeButton("0");
	private CardPanel cardPanel=new CardPanel();
	private int sumPoint=0;
	private int oppSumPoint=0;

	public PlayerPanel() {
		BoxLayout L = new BoxLayout(this, BoxLayout.X_AXIS);
		this.add(Box.createRigidArea(new Dimension(0, 100)));
		// sumPoint = new typeButton("分数");
		// cardPanel = new CardPanel();
		this.add(sumPointBut);
		this.add(cardPanel);
	}

	public void addCard(String cardName,int cardPoint){ //添加卡牌
		cardPanel.addCard(cardName,cardPoint);
		sumPoint=cardPanel.getSumPoint();
		sumPointBut.setText(String.valueOf(sumPoint));
	}

	public void addHideCard(String cardName,int cardPoint, boolean backFace){ //添加显示卡背的卡牌
		cardPanel.addHideCard(cardName,cardPoint,true);
		oppSumPoint=cardPanel.getOppSumPoint();
		sumPoint=cardPanel.getSumPoint();
		sumPointBut.setText(String.valueOf(oppSumPoint));
	}

	public int getSumPoint() { //获取实际的分数
		return sumPoint;
	}

	public int getOppSumPoint() { //获取显示的分数
		return oppSumPoint;
	}
}

class CardPanel extends JPanel { //卡牌面板
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int sumPoint = 0;
	private int oppSumPoint=0;
	public ArrayList <CardContainer> cardContainers  = new ArrayList<CardContainer>();

	public void addCard(String cardName,int cardPoint){
		CardContainer c = new CardContainer(cardName,cardPoint);
		sumPoint += c.card.cardPoint;
		this.add(c);
		cardContainers.add(c);
	}
	public void addHideCard(String cardName,int cardPoint, boolean backFace){
		CardContainer c= new CardContainer(cardName,cardPoint,true);
		sumPoint += c.card.cardPoint;
		this.add(c);
		cardContainers.add(c);

	}

	public int getSumPoint(){
		if(sumPoint>21){//如果总点数大于21点则A按1点计算
			sumPoint=0;
			for (int i=0;i<=cardContainers.size()-1;i++){
				if(cardContainers.get(i).card.cardPointOpt==1)
					sumPoint+=1;
				else
					sumPoint+=cardContainers.get(i).card.cardPoint;
			}
		}
		return sumPoint;
	}

	public int getOppSumPoint(){
		for (int i=0;i<=cardContainers.size()-2;i++){
			oppSumPoint+=cardContainers.get(i).card.cardPoint;

		}
		return oppSumPoint;

	}
}

class CardContainer extends JLabel { //卡牌容器 每个容器装有一张单独的卡牌
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// private static Cards cards = new Cards(); //牌库
	private ImageIcon i =null;
	public Card card;
	public boolean backFace=false;

	public CardContainer(String cardName,int cardPoint) {
		card = new Card(cardName, cardPoint);
		i = new ImageIcon(card.cardName);
		this.setIcon(i);
	}

	public CardContainer(String cardName,int cardPoint, boolean backFace) { //对手最后一张卡牌显示为卡背
		card = new Card(cardName, cardPoint);
		this.backFace=backFace;

		if(backFace){
			i= new ImageIcon("pukeImage/back.jpg");
		}
		else {
			i = new ImageIcon(card.cardName);
		}
		i.setImage(i.getImage().getScaledInstance(105, 150, Image.SCALE_DEFAULT));
		this.setIcon(i);
	}


	public void turnFace(){ //翻转卡背
		i = new ImageIcon(card.cardName);
		i.setImage(i.getImage().getScaledInstance(105, 150, Image.SCALE_DEFAULT));
		backFace=false;
		this.setIcon(i);

	}
}

class Cards{   //牌组
	public Card cardDataBase[][] = new Card[15][5];
	// public HashSet<Card> usedCard = new HashSet<Card>();

	public Cards(){
		initiaCards();
	}

	private void initiaCards(){
		for (int i=2;i<=14;i++){
			for(int j=1;j<=4;j++){
				cardDataBase[i][j]=new Card(Name(i, j),i);
			}
		}
	}
	private String Name(int i,int j){
		String name ="pukeImage/";
		switch (i) {
			case 11:
				name+="J";
				break;
			case 12:
				name+="Q";
				break;
			case 13:
				name+="K";
				break;
			case 14:
				name+="A";
				break;
		
			default:
				name+=i;
				break;
		}
		name+="_";
		name+=j;
		name+=".jpg";

		return name;
	}

	public Card getCard(String cardName){ //根据服务器发来的卡名返回卡牌
		Card tmp=null;
		// do {
		// 	Random random = new Random();
		// 	int i= random.nextInt(13)+2;
		// 	int j= random.nextInt(4)+1;
		// 	tmp = cardDataBase[i][j];
			
		// } while (usedCard.contains(tmp));
		// usedCard.add(tmp);
		// System.out.println(tmp.cardName);
		for (int i=2;i<14;i++){
			for(int j=1;j<=4;j++){
				if(cardDataBase[i][j].cardName.equals(cardName))
					return cardDataBase[i][j];
			}
		}
		return tmp;

	}

}

class Card{   //卡牌基本类 包含每张卡牌的基本信息

	public String cardName; //卡牌名称，也是卡牌的图片路径
	public int cardPoint;  //卡牌分值
	public int cardPointOpt;

	public Card(String cardName,int cardPoint){
		this.cardName = cardName;
		if (cardPoint>10){
			this.cardPoint=10;
			if(cardPoint==13)
			{
				this.cardPointOpt=1;
				this.cardPoint=11;
			}
		}

		else{
			this.cardPoint=cardPoint;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(this.cardName != ((Card)obj).cardName)
			return false;

		if (obj == null)
			return false;

		if(obj instanceof Card){
			Card card = (Card)obj;
			if(card.cardName.equals(this.cardName))
				return true;
		}

		return false;
	}

	@Override
	public int hashCode(){
		return this.cardName.hashCode()+cardPoint;
	}
}

