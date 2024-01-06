package cn.wdx.chatbot.api.domain.baiduchat.service.impl;


import cn.wdx.chatbot.api.domain.baiduchat.model.req.Message;
import cn.wdx.chatbot.api.domain.baiduchat.model.req.SimpleChatReq;
import cn.wdx.chatbot.api.domain.baiduchat.model.resp.AccessTokenResp;
import cn.wdx.chatbot.api.domain.baiduchat.model.resp.SimpleChatResp;
import cn.wdx.chatbot.api.domain.baiduchat.service.IBaiduService;
import cn.wdx.chatbot.api.domain.common.utils.OkHttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiduServiceImpl implements IBaiduService {
    private static final String ACCESS_TOKEN_URL_FORMAT = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s";
    private static final String ERNIE_BOT_URL_FORMAT = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=%s";

    private static final String USER = "user";
    @Override
    public String getAccessToken(String clientId, String clientSecret) {
        String url = String.format(ACCESS_TOKEN_URL_FORMAT, clientId, clientSecret);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        AccessTokenResp accessTokenResp = OkHttpUtil.post(url, "", headers, AccessTokenResp.class);
        return accessTokenResp.getAccess_token();
    }

    @Override
    public String getSimpleQuestionAnswer(String access_token, String content) {
        String url = String.format(ERNIE_BOT_URL_FORMAT, access_token);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        SimpleChatReq simpleChatReq = new SimpleChatReq();
        List<Message> messages = new ArrayList<>();
        Message message = new Message(USER, content);
        messages.add(message);
        simpleChatReq.setMessages(messages);
        SimpleChatResp chatResp = OkHttpUtil.post(url, JSONObject.toJSONString(simpleChatReq), headers, SimpleChatResp.class);
        return chatResp.getResult();
    }
}
