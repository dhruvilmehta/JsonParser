package src.frontend.parsers;

import src.frontend.Token;
import src.frontend.TokenType;
import src.frontend.json.JsonParser;
import src.intermediate.ICodeFactory;
import src.intermediate.ICodeNode;
import src.intermediate.icodeimpl.ICodeKeyImpl;
import src.intermediate.icodeimpl.ICodeNodeTypeImpl;

import static src.frontend.TokenType.*;

public class ObjectParser extends JsonParser {

    public ObjectParser(JsonParser parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        token = nextToken(); // Consuming the {
        ICodeNode objectNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.OBJECT);
        while (token.getType() != TokenType.CLOSE_CURLY_BRACE) {
            if (token.getType() != TokenType.STRING_LITERAL)
                throw new Exception("Invalid key in JSON object. Expected a string literal.");

            String key = token.getText();
            ICodeNode keyNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.STRING_LITERAL);
            keyNode.setAttribute(ICodeKeyImpl.VALUE, key);

            token = nextToken();
            if (token.getType() != COLON)
                throw new Exception("Expected : after key in JSON at line " + token.getLineNumber());
            token = nextToken();

            ICodeNode valueNode = parseValue(token);
            ICodeNode keyPairNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.PAIR_NODE);
            keyPairNode.addChild(keyNode);
            keyPairNode.addChild(valueNode);
            objectNode.addChild(keyPairNode);

            token = nextToken();
            if (token.getType() == TokenType.COMMA)
                token = nextToken();
            else if (token.getType() == TokenType.CLOSE_CURLY_BRACE)
                break;
            else
                throw new Exception("Expected ',' or '}' in JSON object.");
        }

        return objectNode;
    }
}
