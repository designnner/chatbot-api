package cn.wdx.chatbot.api.domain.qfchat.Impl;

import cn.wdx.chatbot.api.domain.model.aggregates.QfAggregates;
import cn.wdx.chatbot.api.domain.qfchat.IOpenAi;
import cn.wdx.chatbot.api.infrastructure.access.PostReq;
import cn.wdx.chatbot.api.infrastructure.factory.CloseableHttpClientFactory;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-04 20:30
 */

@Service
public class OpenAi implements IOpenAi {

    private Logger logger = LoggerFactory.getLogger(OpenAi.class);


    private static final String address = "https://appbuilder.baidu.com/rpc/2.0/cloud_hub/v1/ai_engine/agi_platform/v1/instance/integrated";

    private static final String key = "bce-v3/ALTAK-4RqIz4USsvYhFrgxzfih2/422b323548bc81aa0f96065de2f4b4ddf3708a01";

    /**
     * @description:
     * @param: text
     * @return: java.lang.String
     * @author wudanxin
     * @date: 2024-01-04 20:31
     */
    @Override
    public String answerByAi(String text) throws IOException {
        HttpClient httpClient = new CloseableHttpClientFactory().getHttpClient();

        HttpPost post = new PostReq().initPost(address, key).setPrompt(text);

        HttpResponse response = httpClient.execute(post);


        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());


            QfAggregates result = JSON.parseObject(jsonStr, QfAggregates.class);

            logger.info("调用接口成功。 返回的数据为：{}",jsonStr);

            String answer = result.getResult().getAnswer();

            if(null != answer && !answer.isEmpty()) return answer;

            return "答案为null";

        }

        throw new RuntimeException("请求失败。"+response.getStatusLine());

    }
}
