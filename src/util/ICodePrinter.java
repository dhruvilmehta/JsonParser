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
        // Indentation for readability
        String indent = "  ".repeat(level);

        // Print the node type
        ps.println(indent + "Node Type: " + node.getType());

        // Print the node attributes
        Map<ICodeKey, Object> attributes = node.getAttributes();
        if (!attributes.isEmpty()) {
            ps.println(indent + "  Attributes:");
            for (Map.Entry<ICodeKey, Object> entry : attributes.entrySet()) {
                ps.println(indent + "    " + entry.getKey() + ": " + entry.getValue());
            }
        }

        // Recursively print child nodes
        List<ICodeNode> children = node.getChildren();
        if (!children.isEmpty()) {
            ps.println(indent + "Children:");
            for (ICodeNode child : children) {
                printNode(child, level + 1);
            }
        }
    }
}
