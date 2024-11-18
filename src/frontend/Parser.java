package src.frontend;

import src.intermediate.ICode;
import src.message.Message;
import src.message.MessageHandler;
import src.message.MessageListener;
import src.message.MessageProducer;

public abstract class Parser implements MessageProducer {
    Scanner scanner;
    MessageHandler messageHandler;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
        this.messageHandler = new MessageHandler();
    }

    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public abstract ICode parse() throws Exception;

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}
