package cn.wdx.chatbot.api.domain.baiduchat.service;


import cn.wdx.chatbot.api.domain.baiduchat.model.resp.AccessTokenResp;

public interface IBaiduService {
    AccessTokenResp getAccessToken(String clientId, String clientSecret);

    String getSimpleQuestionAnswer(String access_token, String question);
}
