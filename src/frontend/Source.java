package src.frontend;

import java.io.BufferedReader;
import java.io.IOException;

import src.message.Message;
import src.message.MessageHandler;
import src.message.MessageListener;
import src.message.MessageProducer;
import src.message.MessageType;

public class Source implements MessageProducer {
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;
    private String line;
    private int lineNum;
    private int currPos;
    private BufferedReader reader;
    private MessageHandler messageHandler;

    public Source(BufferedReader reader) {
        this.lineNum = 0;
        this.currPos = -2;
        this.reader = reader;
        this.messageHandler = new MessageHandler();
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getPosition() {
        return currPos;
    }

    public void setPosition(int pos) {
        currPos = pos;
    }

    public char currentChar() throws IOException {
        if (currPos == -2) {
            readLine();
            return nextChar();
        } else if (line == null)
            return EOF;
        else if (currPos == -1 || currPos == line.length())
            return EOL;
        else if (currPos > line.length()) {
            readLine();
            return nextChar();
        } else
            return line.charAt(currPos);
    }

    public char nextChar() throws IOException {
        currPos++;
        return currentChar();
    }

    private void readLine() throws IOException {
        line = reader.readLine();
        currPos = -1;
        if (line != null) {
            ++lineNum;
        }
        if (line != null) {
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[] { lineNum, line }));
        }
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    @Override
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    @Override
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

}
