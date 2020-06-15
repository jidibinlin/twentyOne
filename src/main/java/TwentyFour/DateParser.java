package TwentyFour;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.annotation.*;

public class DateParser {

    public static InitVs parseInitVS(String initVs) {
        InitVs foo =JSON.parseObject(initVs, InitVs.class);
        return foo;
    }

    public static SendCard parseSendCard(String sendCard) {
        SendCard foo = JSON.parseObject(sendCard, SendCard.class);
        return foo;
    }

    public static String getDateType(String foo) {
        String result;
        String[] strArry = foo.split(",");
        result = strArry[0].split(":")[1].replace("\"", "");
        return result;

    }

}
class InitVs{
    @JSONField(ordinal = 1)
    private String type = "init";
    @JSONField(ordinal = 2)
    private String clientName;
    @JSONField(ordinal = 3)
    private String first; //client1 client2
    @JSONField(ordinal = 4)
    private PreCards client1=null;
    @JSONField(ordinal = 5)
    private PreCards client2=null;

    public InitVs(){

    }

    public InitVs(PreCards client1,PreCards client2,String first,String clientName){
        this.client1=client1;
        this.client2=client2;
        this.clientName=clientName;
        this.first=first;

    }

    class PreCards{
        private String cardName1=null;
        private String cardName2=null;
        public PreCards(){

        }

        public PreCards(String cardName1,String cardName2){
            this.cardName1=cardName1;
            this.cardName2=cardName2;
        }

        public String getCardName1() {
            return cardName1;
        }

        public void setCardName1(String cardName1) {
            this.cardName1 = cardName1;
        }

        public String getCardName2() {
            return cardName2;
        }

        public void setCardName2(String cardName2) {
            this.cardName2 = cardName2;
        }
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PreCards getClient1() {
        return client1;
    }

    public void setClient1(PreCards client1) {
        this.client1 = client1;
    }

    public PreCards getClient2() {
        return client2;
    }

    public void setClient2(PreCards client2) {
        this.client2 = client2;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

class SendCard {

    @JSONField(ordinal = 1)
    private String type = "sendCard";
    @JSONField(ordinal = 2)
    private String destClient; // client1 client2
    @JSONField(ordinal = 3)
    private String cardName; // generate by cards

    public SendCard(){

    }

    public SendCard(String destClient, String cardName) {
        this.destClient = destClient;
        this.cardName = cardName;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestClient() {
        return destClient;
    }

    public void setDestClient(String destClient) {
        this.destClient = destClient;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
