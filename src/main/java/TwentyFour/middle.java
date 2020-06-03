package TwentyFour;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class MiddlePanel extends JPanel {

	public JPanel top;
	public JPanel bottom;

	public MiddlePanel() {
		this.setLayout(new GridLayout(2, 1, 100, 100));

		top = new PlayerPanel(new typeButton("用户一点数"), new JLabel(new ImageIcon("pukeImage/2_1.jpg")));
		bottom = new PlayerPanel(new typeButton("用户二点数"), new JLabel(new ImageIcon("pukeImage/2_2.jpg")));

		this.add(top);
		this.add(bottom);
	}
}

class PlayerPanel extends JPanel {   //玩家面板

	typeButton sumPoint;
	JLabel card;

	public PlayerPanel(typeButton sumPoint, JLabel card) {
		BoxLayout L = new BoxLayout(this, BoxLayout.X_AXIS);
		this.add(Box.createRigidArea(new Dimension(0, 100)));
		this.add(sumPoint);
		this.add(card);
		CardPanel a = new CardPanel();
	}
}

class CardPanel extends JPanel { //卡牌面板
	private int sumPoint = 0;

	public void addCard(CardContainer foo) {
		this.add(foo);
	}
}

class CardContainer extends JLabel {
	static Cards cards = new Cards();



}

class Cards{   //用来吐出唯一的卡牌
	public Card cardDataBase [][]={};
	public HashSet<Card> usedCard = new HashSet<Card>();

	private void initiaCards(){
		for (int i=2;i<=14;i++){
			for(int j=1;j<=4;j++){
				cardDataBase[i][j]=new Card(Name(i, j),i);
			}
		}
	}
	private String Name(int i,int j){
		String name ="";
		switch (i) {
			case 11:
				name+="j";
				break;
			case 12:
				name+="Q";
				break;
			case 13:
				name+="k";
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
			int i= random.nextInt(20)%14+1;
			int j= random.nextInt(20)%14+1;
			tmp = cardDataBase[i][j];
			
		} while (usedCard.contains(tmp));
		usedCard.add(tmp);

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
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
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

