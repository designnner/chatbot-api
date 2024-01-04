package cn.wdx.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-02 19:00
 */
public class ApiTest {
    @Test
    public void query_unanswered_question() throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51112881214284/topics?scope=unanswered_questions&count=20");
        get.addHeader("Cookie","zsxq_access_token=E49BE3C8-AC46-A312-7A00-8FCF70FC9E67_828E319B0BF58777; zsxqsessionid=195ee8cc726674deb147c3b0869d062b; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22185555111111242%22%2C%22first_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjODY3NmRmYzAxNy0wZDA1NDQ3YTM0YWNjNi0yNjAwMTk1MS05MjE2MDAtMThjODY3NmRmYzExMDQzIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg1NTU1MTExMTExMjQyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22185555111111242%22%7D%2C%22%24device_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%7D");
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = client.execute(get);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());



            System.out.println(s);
        }
        else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


    @Test
    public void answer_question() throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/411258552151848/answer");
        post.addHeader("Cookie","zsxq_access_token=E49BE3C8-AC46-A312-7A00-8FCF70FC9E67_828E319B0BF58777; zsxqsessionid=195ee8cc726674deb147c3b0869d062b; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22185555111111242%22%2C%22first_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjODY3NmRmYzAxNy0wZDA1NDQ3YTM0YWNjNi0yNjAwMTk1MS05MjE2MDAtMThjODY3NmRmYzExMDQzIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg1NTU1MTExMTExMjQyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22185555111111242%22%7D%2C%22%24device_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%7D");
        post.addHeader("Content-Type","application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse res = httpclient.execute(post);

        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            System.out.println(EntityUtils.toString(res.getEntity()));
        }
        else{
            System.out.println("回答失败："+res.getStatusLine().getStatusCode()+"!!!");
        }


    }

    @Test
    public void tt() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/411258552152258/answer");
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("Cookie","zsxq_access_token=E49BE3C8-AC46-A312-7A00-8FCF70FC9E67_828E319B0BF58777; zsxqsessionid=195ee8cc726674deb147c3b0869d062b; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22185555111111242%22%2C%22first_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjODY3NmRmYzAxNy0wZDA1NDQ3YTM0YWNjNi0yNjAwMTk1MS05MjE2MDAtMThjODY3NmRmYzExMDQzIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg1NTU1MTExMTExMjQyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22185555111111242%22%7D%2C%22%24device_id%22%3A%2218c8676dfc017-0d05447a34acc6-26001951-921600-18c8676dfc11043%22%7D");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }


    }
}
