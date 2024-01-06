package cn.wdx.chatbot.api.test;

import cn.wdx.chatbot.api.domain.baiduchat.service.IBaiduService;
import cn.wdx.chatbot.api.domain.qfchat.IOpenAi;

import cn.wdx.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wdx.chatbot.api.domain.zsxq.model.aggregates.ZhipuAggregates;
import cn.wdx.chatbot.api.domain.zsxq.model.vo.Topics;
import cn.wdx.chatbot.api.domain.zsxq.service.IZsxqApi;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 11:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {
    private Logger logger = LoggerFactory.getLogger(TestApplication.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${baidu.apikey}")
    private String baiduApiKey;
    @Value("${baidu.secretKey}")
    private String baiduSecretKey;

    @Resource
    private IZsxqApi zsxqApi;


    @Resource
    private IOpenAi openAi;






    @Resource
    private IBaiduService baiduService;

    @Test
    public void testZxsqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnansweredQuestionsTopicId(groupId, cookie);

        logger.info("test。");

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();

        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            // 回答问题
//            zsxqApi.answer(groupId, cookie, topicId, openAI.doChatGPT(openAiKey, text), false);
        }

    }

    @Test
    public void testOpenAi() throws IOException {

        //POST https://open.bigmodel.cn/api/paas/v3/model-api/{model}/{invoke_method}

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://open.bigmodel.cn/api/paas/v3/model-api/chatglm_turbo/invoke");

        post.addHeader("Content-Type","application/json; charset=UTF-8");

        //添加jwt鉴权信息
        //83fabf9b9e129dade977e5c8aa18a3a1.PAOodt4aqI7qHq9v
        //2be833648fd1a4d540cd598d90a218f8.KFSlobeU3GMuDdKg

        String apiSecretKey = "2be833648fd1a4d540cd598d90a218f8.KFSlobeU3GMuDdKg";
        String apiSecret = apiSecretKey.split("\\.")[1];
        String apiKey = apiSecretKey.split("\\.")[0];

        Map<String,Object> head = new HashMap<>();
        //"alg": "HS256", "sign_type": "SIGN"
        head.put("alg","HS256");
        head.put("sign_type","SIGN");

        Map<String,String> playLoads = new HashMap<>();
        playLoads.put("api_key",apiKey);
        playLoads.put("exp", String.valueOf(System.currentTimeMillis()+60*1000L));
        playLoads.put("timestamp",String.valueOf(System.currentTimeMillis()));


        Algorithm algorithm = Algorithm.HMAC256(apiSecret.getBytes(StandardCharsets.UTF_8));

        String sign = JWT.create().withPayload(playLoads).withHeader(head).sign(algorithm);


        post.addHeader("Authorization","Bearer "+sign);
        String jsonStr = "{\n" +
                " \"prompt\": [{\"role\": \"user\", \"content\": \"你好\"},\n" +
                " {\"role\":\"assistant\",\"content\":\"\\\" 您好！我是人工智能助手，我能为您提供帮助。请问您有什么问题或需要解决的事情吗？请用中文提问，我会尽力回答您的问题。\\\"\"},\n" +
                " {\"role\": \"user\",\"content\": \"写一个冒泡排序\"}\n" +
                "        ]\n" +
                "}";
        StringEntity stringEntity = new StringEntity(jsonStr);
        post.setEntity(stringEntity);


        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            HttpEntity entity = response.getEntity();
            String resjsonStr = EntityUtils.toString(entity);

            logger.info("请求成功。entity：{}",entity);

            ZhipuAggregates zhipuAggregates = JSON.parseObject(resjsonStr, ZhipuAggregates.class);

            byte[] res = zhipuAggregates.getData().getChoices().get(0).getContent().getBytes(StandardCharsets.UTF_8);

            System.out.println(new String(res,Charset.forName("UTF-8")));

        }else{
            logger.error("请求失败。err：{}",EntityUtils.toString(response.getEntity()));
        }



    }

    @Test
    public void testGetAccessTokenResp() {
        String accessToken = baiduService.getAccessToken("x", "o");
    }

    @Test
    public void testSimpleChat() {
        String accessToken = baiduService.getAccessToken(baiduApiKey, baiduSecretKey);
        String resp = baiduService.getSimpleQuestionAnswer(accessToken, "1+1等于几啊");
        System.out.println(resp);
    }


}

