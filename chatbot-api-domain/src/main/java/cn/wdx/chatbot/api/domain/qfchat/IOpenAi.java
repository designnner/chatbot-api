package cn.wdx.chatbot.api.domain.qfchat;

import java.io.IOException;

public interface IOpenAi {
    public String answerByAi(String text) throws IOException;
}
