package cn.wdx.chatbot.api.domain.zsxq.model.aggregates;

import cn.wdx.chatbot.api.domain.zsxq.model.res.RespData;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 11:30
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

}
