package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class NullToken extends Token {
    public NullToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        type = TokenType.NULL_LITERAL;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char current = currentChar();
            if (Character.isAlphabetic(current))
                str.append(current);
            else
                break;
            nextChar();
        }

        type = TokenType.NULL_LITERAL;
        text = str.toString();
        value = str.toString();
    }
}
