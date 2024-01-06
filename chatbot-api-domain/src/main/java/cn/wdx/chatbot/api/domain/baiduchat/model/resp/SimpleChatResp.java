package cn.wdx.chatbot.api.domain.baiduchat.model.resp;


public class SimpleChatResp {

    private String id;
    private String object;
    private long created;
    private String result;
    private boolean is_truncated;
    private boolean need_clear_history;
    private Usage usage;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setObject(String object) {
        this.object = object;
    }
    public String getObject() {
        return object;
    }

    public void setCreated(long created) {
        this.created = created;
    }
    public long getCreated() {
        return created;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }

    public void setIs_truncated(boolean is_truncated) {
        this.is_truncated = is_truncated;
    }
    public boolean getIs_truncated() {
        return is_truncated;
    }

    public void setNeed_clear_history(boolean need_clear_history) {
        this.need_clear_history = need_clear_history;
    }
    public boolean getNeed_clear_history() {
        return need_clear_history;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public Usage getUsage() {
        return usage;
    }

}