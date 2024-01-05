package cn.wdx.chatbot.api.domain.common.utils;


import cn.wdx.chatbot.api.domain.baiduchat.model.resp.AccessTokenResp;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpUtil {
    private static final OkHttpClient client = new OkHttpClient.Builder().build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String get(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String post(String url, String json, @Nullable Map<String, String> mapForHeader) {
        RequestBody requestBody = RequestBody.create(JSON, json);
        Headers headers = getHeaders(mapForHeader);
        Request request = new Request.Builder()
                .post(requestBody)
                .headers(headers)
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T post(String url, String json, @Nullable Map<String, String> mapForHeader, Class<T> clz) {
        return parseJSONString(post(url, json, mapForHeader), clz);
    }

    public static Headers getHeaders(Map<String, String> map) {
        Headers.Builder builder = new Headers.Builder();
        if (map != null && map.size() == 0) {
            for (String key : map.keySet()) {
                builder.add(key, map.get(key));
            }
        }
        return builder.build();
    }

    public static <T> T parseJSONString(String json, Class<T> clz) {
        return JSONObject.parseObject(json, clz);
    }
}
