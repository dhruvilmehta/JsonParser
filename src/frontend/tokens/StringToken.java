package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class StringToken extends Token {
    public StringToken(Source source) throws Exception {
        super(source);
        extractToken();
    }

    protected void extractToken() throws Exception {
        char currentChar = currentChar();
        StringBuilder str = new StringBuilder();

        while (Character.isLetterOrDigit(currentChar)) {
            str.append(currentChar);
            currentChar = nextChar();
        }
        text = str.toString();
        type = TokenType.STRING_LITERAL;
        value = str.toString();
        nextChar(); // consuming the closing "
    }
}
