package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class BooleanToken extends Token {
    public BooleanToken(Source source) throws Exception {
        super(source);
    }

    @Override
    protected void extract() throws Exception {
        type = TokenType.BOOLEAN_LITERAL;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char current = currentChar();
            if (Character.isAlphabetic(current))
                str.append(current);
            else
                break;
            nextChar();
        }

        type = TokenType.BOOLEAN_LITERAL;
        text = str.toString();
        value = str.toString();
    }
}
