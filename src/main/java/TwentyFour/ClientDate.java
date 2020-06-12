package TwentyFour;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.annotation.*;

public class ClientDate {

    public static String genRequestCard(String type,String client){
        String result="";
        result =JSON.toJSONString(new requestCard(type, client));
        return result;
    }
}

class requestCard{
    @JSONField(ordinal = 1)
    private String type;    
    @JSONField(ordinal = 2)
    private String client;

    public  requestCard() {
        
    }

    public requestCard(String type,String client) {
        this.type = type;
        this.client = client;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
}

