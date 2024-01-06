package cn.wdx.chatbot.api.infrastructure.factory;

import org.apache.http.client.HttpClient;

public interface HttpClientFactory {
    HttpClient getHttpClient();
}
