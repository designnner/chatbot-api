package cn.wdx.chatbot.api.infrastructure.access;

import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public interface IPostReq {

    IPostReq initPost(String address,String key);

    HttpPost setPrompt(String text) throws UnsupportedEncodingException;

}
