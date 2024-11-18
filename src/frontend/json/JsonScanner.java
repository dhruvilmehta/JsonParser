package src.frontend.json;

import src.frontend.Scanner;
import src.frontend.Source;
import src.frontend.Token;
import src.frontend.tokens.BooleanToken;
import src.frontend.tokens.CommaToken;
import src.frontend.tokens.EofToken;
import src.frontend.tokens.ErrorToken;
import src.frontend.tokens.NullToken;
import src.frontend.tokens.NumericToken;
import src.frontend.tokens.StringToken;
import src.frontend.tokens.SymbolToken;

import static src.frontend.Source.EOF;

public class JsonScanner extends Scanner {
    public JsonScanner(Source source) {
        super(source);
    }

    protected Token extractToken() throws Exception {
        skipWhiteSpace();
        Token token;
        char currentChar = currentChar();
        if (currentChar == EOF)
            token = new EofToken(source);
        else if (currentChar == ':' || currentChar == '{' || currentChar == '}' || currentChar == '['
                || currentChar == ']')
            token = new SymbolToken(source);
        else if (currentChar == '"')
            token = new StringToken(source);
        else if (Character.isDigit(currentChar))
            token = new NumericToken(source);
        else if (isBooleanLiteral())
            token = new BooleanToken(source);
        else if (currentChar == ',')
            token = new CommaToken(source);
        else if (isNullLiteral())
            token = new NullToken(source);
        else
            token = new ErrorToken(source);

        return token;
    }

    private boolean isBooleanLiteral() throws Exception {
        int position = source.getPosition();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char current = currentChar();
            if (Character.isAlphabetic(current))
                str.append(current);
            else
                break;
            nextChar();
        }

        source.setPosition(position);
        if (str.toString().equals("true") || str.toString().equals("false"))
            return true;

        return false;
    }

    private boolean isNullLiteral() throws Exception {
        int position = source.getPosition();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char current = currentChar();
            if (Character.isAlphabetic(current))
                str.append(current);
            else
                break;
            nextChar();
        }

        boolean result = false;
        if (str.toString().equals("null"))
            result = true;

        source.setPosition(position);
        return result;

    }

    private void skipWhiteSpace() throws Exception {
        char currentChar = currentChar();
        while (Character.isWhitespace(currentChar)) {
            currentChar = nextChar();
        }
    }
}
