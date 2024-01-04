package cn.wdx.chatbot.api.domain.model.aggregates;

import cn.wdx.chatbot.api.domain.model.vo.Data;

import java.io.Serializable;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 20:10
 */
public class ZhipuAggregates implements Serializable {

    private int code;

    private String msg;

    private Data data;

    private boolean success;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }

}
