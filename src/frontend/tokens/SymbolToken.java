package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class SymbolToken extends Token {
    public SymbolToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        char current = currentChar();
        text = Character.toString(current);
        value = Character.toString(current);
        switch (current) {
            case ':':
                type = TokenType.COLON;
                break;
            case '{':
                type = TokenType.OPEN_CURLY_BRACE;
                break;
            case '}':
                type = TokenType.CLOSE_CURLY_BRACE;
                break;
            case '[':
                type = TokenType.OPEN_SQUARE_BRACE;
                break;
            case ']':
                type = TokenType.CLOSE_SQUARE_BRACE;
                break;
        }
        nextChar();
    }
}
