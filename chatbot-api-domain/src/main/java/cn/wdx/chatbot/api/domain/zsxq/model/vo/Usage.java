package cn.wdx.chatbot.api.domain.zsxq.model.vo;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 20:09
 */
public class Usage
{
    private int prompt_tokens;

    private int completion_tokens;

    private int total_tokens;

    public void setPrompt_tokens(int prompt_tokens){
        this.prompt_tokens = prompt_tokens;
    }
    public int getPrompt_tokens(){
        return this.prompt_tokens;
    }
    public void setCompletion_tokens(int completion_tokens){
        this.completion_tokens = completion_tokens;
    }
    public int getCompletion_tokens(){
        return this.completion_tokens;
    }
    public void setTotal_tokens(int total_tokens){
        this.total_tokens = total_tokens;
    }
    public int getTotal_tokens(){
        return this.total_tokens;
    }
}