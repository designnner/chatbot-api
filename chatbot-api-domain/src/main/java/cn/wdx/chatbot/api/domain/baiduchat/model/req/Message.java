package cn.wdx.chatbot.api.domain.baiduchat.model.req;


public class Message {
    enum RoleType {
        user,
        assistant,
        function;
    }
    private String role;
    private String content;
    private String name;
    private FunctionCall function_call;

    public Message(String role, String content) {
        this(role, content, null, null);
    }

    public Message(String role, String content, String name, FunctionCall function_call) {
        this.role = role;
        this.content = content;
        this.name = name;
        this.function_call = function_call;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FunctionCall getFunction_call() {
        return function_call;
    }

    public void setFunction_call(FunctionCall function_call) {
        this.function_call = function_call;
    }
}
