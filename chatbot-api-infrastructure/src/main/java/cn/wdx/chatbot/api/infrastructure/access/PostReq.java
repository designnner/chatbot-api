package cn.wdx.chatbot.api.infrastructure.access;

import javafx.geometry.Pos;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-06 11:13
 */
public class PostReq implements IPostReq{

    private Logger logger = LoggerFactory.getLogger(Pos.class);
    private HttpPost post;

    public PostReq() {
    }

//    private PostReq(String address, String key){
//        this.post = new HttpPost(address);
//        post.addHeader("Content-Type","application/json");
//        post.addHeader("X-Appbuilder-Authorization","Bearer "+key);
//    }

    @Override
    public IPostReq initPost(String address, String key) {

        this.post = new HttpPost(address);
        post.addHeader("Content-Type","application/json");
        post.addHeader("X-Appbuilder-Authorization","Bearer "+key);
        return this;
    }


    public HttpPost setPrompt(String text) throws UnsupportedEncodingException {
        String prompt = "{\"query\":\""+text+"\",\"response_mode\":\"blocking\"}";

        post.setEntity(new StringEntity(prompt,Charset.forName("UTF-8")));



        logger.info("请求body：{}",prompt);


        return this.post;
    }
}
