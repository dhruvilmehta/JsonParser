package src.util;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import src.intermediate.*;

public class ICodePrinter {
    PrintStream ps;

    public ICodePrinter(PrintStream ps) {
        this.ps = ps;
    }

    public void printICode(ICodeNode root) {
        printNode(root, 0);
    }

    private void printNode(ICodeNode node, int level) {
        String indent = "  ".repeat(level);
        ps.println(indent + "Node Type: " + node.getType());

        Map<ICodeKey, Object> attributes = node.getAttributes();
        if (!attributes.isEmpty()) {
            ps.println(indent + "  Attributes:");
            for (Map.Entry<ICodeKey, Object> entry : attributes.entrySet())
                ps.println(indent + "    " + entry.getKey() + ": " + entry.getValue());
        }

        List<ICodeNode> children = node.getChildren();
        if (!children.isEmpty()) {
            ps.println(indent + "Children:");
            for (ICodeNode child : children)
                printNode(child, level + 1);
        }
    }
}
