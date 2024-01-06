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

    @Override
    public String toString() {
        return "SimpleChatReq{" +
                "messages=" + messages +
                '}';
    }
}
