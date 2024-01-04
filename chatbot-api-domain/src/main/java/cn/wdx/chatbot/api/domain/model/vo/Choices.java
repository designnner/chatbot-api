package cn.wdx.chatbot.api.domain.model.vo;

import java.io.Serializable;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 20:09
 */
public class Choices implements Serializable
{
    private String role;

    private String content;

    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
}