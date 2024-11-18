package src.message;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {
    public Message message;
    public List<MessageListener> listeners;

    public MessageHandler() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (MessageListener listener : listeners)
            listener.messageReceived(message);
    }
}
