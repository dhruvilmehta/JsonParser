package src.frontend;

import src.frontend.json.JsonParser;
import src.frontend.json.JsonScanner;

public class FrontendFactory {
    public static Parser createParser(Source source) {
        Scanner scanner = new JsonScanner(source);
        return new JsonParser(scanner);
    }
}
