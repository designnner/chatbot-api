package cn.wdx.chatbot.api.domain.model.aggregates;

import cn.wdx.chatbot.api.domain.model.res.Result;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-05 11:09
 */
public class QfAggregates
{
    private int code;

    private String message;

    private String trace_id;

    private int time;

    private Result result;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setTrace_id(String trace_id){
        this.trace_id = trace_id;
    }
    public String getTrace_id(){
        return this.trace_id;
    }
    public void setTime(int time){
        this.time = time;
    }
    public int getTime(){
        return this.time;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }
}