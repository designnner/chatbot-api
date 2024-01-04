package cn.wdx.chatbot.api.domain.zsxq.model.req;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-03 11:29
 */
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}
