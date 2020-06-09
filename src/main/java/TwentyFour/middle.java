package TwentyFour;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

class MiddlePanel extends JPanel {

	public PlayerPanel top=new PlayerPanel();
	public PlayerPanel bottom=new PlayerPanel();
	public int topSumPoint=0;
	public int bottomSumPoint=0;

	public MiddlePanel() {
		this.setLayout(new GridLayout(2, 1, 100, 100));
		// top=new PlayerPanel();
		// bottom=new PlayerPanel();
		this.add(top);
		this.add(bottom);
	}

	public void topAddCard(){
		top.addCard();
	}

	public void bottomAddCard(){
		bottom.addCard();

	}
}

class PlayerPanel extends JPanel {   //玩家面板

	typeButton sumPoint = new typeButton("0");
	CardPanel cardPanel=new CardPanel();

	public PlayerPanel() {
		BoxLayout L = new BoxLayout(this, BoxLayout.X_AXIS);
		this.add(Box.createRigidArea(new Dimension(0, 100)));
		// sumPoint = new typeButton("分数");
		// cardPanel = new CardPanel();
		this.add(sumPoint);
		this.add(cardPanel);
	}

	public void addCard(){
		cardPanel.addCard();
		sumPoint.setText(String.valueOf(cardPanel.getSumPoint()));
	}
}

class CardPanel extends JPanel { //卡牌面板
	private int sumPoint = 0;

	public void addCard(){
		CardContainer c = new CardContainer();
		sumPoint += c.card.cardPoint;
		this.add(c);
	}

	public int getSumPoint(){
		return sumPoint;
	}


}

class CardContainer extends JLabel {
	private static Cards cards = new Cards();
	public Card card;

	public CardContainer() {
		card = cards.getCard();
		Icon i = new ImageIcon(card.cardName);
		this.setIcon(i);
	}

}

class Cards{   //用来吐出唯一的卡牌
	public Card cardDataBase[][] = new Card[15][5];
	public HashSet<Card> usedCard = new HashSet<Card>();

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

	public Card getCard(){
		Card tmp=null;
		do {
			Random random = new Random();
			int i= random.nextInt(13)+2;
			int j= random.nextInt(4)+1;
			tmp = cardDataBase[i][j];
			
		} while (usedCard.contains(tmp));
		usedCard.add(tmp);
		System.out.println(tmp.cardName);

		return tmp;
	}

}

class Card{   //卡牌基本类 包含每张卡牌的基本信息

	public String cardName; //卡牌名称，也是卡牌的图片路径
	public int cardPoint;  //卡牌分值
	public int cardPointAlter;

	public Card(String cardName,int cardPoint){
		this.cardName = cardName;
		if (cardPoint>10){
			this.cardPoint=10;
			if(cardPoint==14)
				this.cardPointAlter=1;
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

