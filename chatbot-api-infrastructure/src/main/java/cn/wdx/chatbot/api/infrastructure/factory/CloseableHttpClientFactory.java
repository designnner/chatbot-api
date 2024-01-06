package cn.wdx.chatbot.api.infrastructure.factory;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author wudanxin
 * @version 1.0
 * @description: 创建closeableHttpClient对象
 * @date 2024-01-06 11:07
 */
public class CloseableHttpClientFactory  implements HttpClientFactory{


    private CloseableHttpClient httpClient;


    @Override
    public HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
