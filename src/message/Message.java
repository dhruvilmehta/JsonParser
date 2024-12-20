package src.message;

public class Message {
    private MessageType type;
    private Object body;

    public Message(MessageType type, Object body) {
        this.type = type;
        this.body = body;
    }

    public Object getBody() {
        return body;
    }

    public MessageType getType() {
        return type;
    }
}
