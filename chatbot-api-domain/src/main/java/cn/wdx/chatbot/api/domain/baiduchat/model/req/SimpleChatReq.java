package cn.wdx.chatbot.api.domain.baiduchat.model.req;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SimpleChatReq {
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static void main(String[] args) {

        SimpleChatReq simpleChatReq = new SimpleChatReq();
        List<Message> messages = new ArrayList<>();
        Message message = new Message("user", "hello");
        messages.add(message);
        simpleChatReq.setMessages(messages);
        String s = JSONObject.toJSONString(simpleChatReq);

        System.out.println(s);


    }
}
