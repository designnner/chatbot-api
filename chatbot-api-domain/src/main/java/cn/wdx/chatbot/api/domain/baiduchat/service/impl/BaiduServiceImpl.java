package cn.wdx.chatbot.api.domain.baiduchat.service.impl;


import cn.wdx.chatbot.api.domain.baiduchat.model.resp.AccessTokenResp;
import cn.wdx.chatbot.api.domain.baiduchat.service.IBaiduService;
import cn.wdx.chatbot.api.domain.common.utils.OkHttpUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BaiduServiceImpl implements IBaiduService {
    private static final String ACCESS_TOKEN_URL_FORMAT = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s";

    @Override
    public AccessTokenResp getAccessToken(String clientId, String clientSecret) {
        String url = String.format(ACCESS_TOKEN_URL_FORMAT, clientId, clientSecret);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return OkHttpUtil.post(url, "", headers, AccessTokenResp.class);
    }

    @Override
    public String getSimpleQuestionAnswer(String access_token, String question) {
        return null;
    }
}
