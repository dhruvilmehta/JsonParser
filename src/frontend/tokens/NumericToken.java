package src.frontend.tokens;

import src.frontend.Source;
import src.frontend.Token;
import src.frontend.TokenType;

public class NumericToken extends Token {
    public NumericToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception {
        char currentChar = currentChar();
        int dots = 0;
        StringBuilder str = new StringBuilder();
        while (Character.isDigit(currentChar) || currentChar == '.') {
            if (currentChar == '.')
                dots++;
            str.append(currentChar);
            currentChar = nextChar();
        }
        if (dots > 1)
            throw new NumberFormatException();
        type = TokenType.NUMERIC_LITERAL;
        text = str.toString();
        if (dots == 0) {
            value = Integer.parseInt(text);
        } else {
            value = Double.parseDouble(text);
        }
    }
}
