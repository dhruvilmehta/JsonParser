package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class CommaToken extends Token {
    public CommaToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception {
        type = TokenType.COMMA;
        text = ",";
        value = ',';
        nextChar();
    }
}
