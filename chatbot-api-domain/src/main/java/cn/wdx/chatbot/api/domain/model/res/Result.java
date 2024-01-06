package cn.wdx.chatbot.api.domain.model.res;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-05 11:08
 */
public class Result
{
    private String answer;

    private String conversation_id;

    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(){
        return this.answer;
    }
    public void setConversation_id(String conversation_id){
        this.conversation_id = conversation_id;
    }
    public String getConversation_id(){
        return this.conversation_id;
    }
}