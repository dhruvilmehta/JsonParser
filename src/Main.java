package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import src.frontend.FrontendFactory;
import src.frontend.Parser;
import src.frontend.Source;
import src.intermediate.ICode;
import src.message.Message;
import src.message.MessageListener;
import src.util.ICodePrinter;

public class Main {
    private Source source;
    private Parser parser;
    private boolean xref;

    public Main(String input, String flags) {
        try {
            xref = flags.indexOf('i') > -1;
            source = new Source(new BufferedReader(new FileReader(input)));
            source.addMessageListener(new SourceMessageListener());
            parser = FrontendFactory.createParser(source);
            parser.addMessageListener(new ParserMessageListener());
            ICode icode = parser.parse();

            if (xref) {
                ICodePrinter printer = new ICodePrinter(System.out);
                printer.printICode(icode.getRoot());
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        try {
            int i = -1;
            String flags = "";
            // Flags.
            while ((++i < args.length) && (args[i].charAt(0) == '-'))
                flags += args[i].substring(1);

            String input = null;
            if (i < args.length)
                input = args[i];
            else
                throw new Exception();

            new Main(input, flags);
        } catch (Exception e) {

        }
    }

    private class SourceMessageListener implements MessageListener {

        @Override
        public void messageReceived(Message message) {
            // System.out.println(((Object[]) (message.getBody()))[1]);
        }
    }

    private class ParserMessageListener implements MessageListener {

        @Override
        public void messageReceived(Message message) {
            System.out.println("Parser Message Received " + message.getType());
        }

    }
}
