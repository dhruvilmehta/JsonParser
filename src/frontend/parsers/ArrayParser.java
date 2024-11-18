package src.frontend.parsers;

import src.frontend.Token;
import src.frontend.TokenType;
import src.frontend.json.JsonParser;
import src.intermediate.ICodeFactory;
import src.intermediate.ICodeNode;
import src.intermediate.icodeimpl.ICodeNodeTypeImpl;

public class ArrayParser extends JsonParser {
    public ArrayParser(JsonParser parent) {
        super(parent);
    }

    public ICodeNode parse(Token token) throws Exception {
        token = nextToken(); // Consume the [
        ICodeNode arrayNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.ARRAY);
        while (token.getType() != TokenType.CLOSE_SQUARE_BRACE) {
            ICodeNode valueNode = parseValue(token);
            arrayNode.addChild(valueNode);

            token = nextToken();

            if (token.getType() == TokenType.COMMA)
                token = nextToken(); // Consume the ,
            else if (token.getType() == TokenType.CLOSE_SQUARE_BRACE)
                break;
            else
                throw new Exception("Expected ',' or ']' in JSON array.");
        }
        return arrayNode;
    }
}
