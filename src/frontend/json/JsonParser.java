package src.frontend.json;

import src.frontend.Parser;
import src.frontend.Scanner;
import src.frontend.Token;
import src.frontend.parsers.ArrayParser;
import src.frontend.parsers.ObjectParser;
import src.intermediate.ICode;
import src.intermediate.ICodeFactory;
import src.intermediate.ICodeNode;
import src.intermediate.icodeimpl.ICodeKeyImpl;
import src.intermediate.icodeimpl.ICodeNodeTypeImpl;
import src.message.Message;
import src.message.MessageType;

public class JsonParser extends Parser {

    public JsonParser(Scanner scanner) {
        super(scanner);
    }

    public JsonParser(JsonParser parent) {
        super(parent.getScanner());
    }

    public ICode parse() throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            Token token = nextToken();
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;

            ICodeNode iCodeNode = parseValue(token);

            ICode iCode = ICodeFactory.createICode();
            iCode.setRoot(iCodeNode);

            sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[] {
                    token.getLineNumber(), elapsedTime }));

            return iCode;
        } catch (Exception e) {
            System.out.println("Exception in JsonParser " + e);
        }
        return null;
    }

    protected ICodeNode parseValue(Token token) throws Exception {
        switch (token.getType()) {
            case OPEN_CURLY_BRACE:
                return new ObjectParser(this).parse(token);
            case OPEN_SQUARE_BRACE:
                return new ArrayParser(this).parse(token);
            case STRING_LITERAL:
                ICodeNode stringNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.STRING_LITERAL);
                stringNode.setAttribute(ICodeKeyImpl.VALUE, token.getText());
                return stringNode;
            case NUMERIC_LITERAL:
                ICodeNode numberNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.NUMERIC_LITERAL);
                numberNode.setAttribute(ICodeKeyImpl.VALUE, token.getText());
                return numberNode;
            case BOOLEAN_LITERAL:
                ICodeNode booleanNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.BOOLEAN_LITERAL);
                booleanNode.setAttribute(ICodeKeyImpl.VALUE, token.getText());
                return booleanNode;
            case NULL_LITERAL:
                ICodeNode nullNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.NULL_LITERAL);
                nullNode.setAttribute(ICodeKeyImpl.VALUE, token.getText());
                return nullNode;
            default:
                throw new Exception("Unexpected value in JSON.");
        }
    }
}
