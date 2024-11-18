package src.backend.yaml;

import static src.frontend.TokenType.STRING_LITERAL;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import src.intermediate.ICodeKey;
import src.intermediate.ICodeNode;
import src.intermediate.ICodeNodeType;
import src.intermediate.icodeimpl.ICodeNodeTypeImpl;

public class YamlConverter {
    private PrintWriter yamlFile;

    public YamlConverter(String fileName) throws Exception {
        this.yamlFile = new PrintWriter(
                new PrintStream(
                        new File(fileName)));
    }

    public void convert(ICodeNode iCodeNode) {
        convertNode(iCodeNode, 0);
    }

    private void convertNode(ICodeNode node, int level) {
        String indent = "  ".repeat(level);
        if (node.getType() == ICodeNodeTypeImpl.PAIR_NODE) {
            ICodeNode keyNode = node.getChildren().get(0);
            ICodeNode valueNode = node.getChildren().get(1);
            if (valueNode.getType() == ICodeNodeTypeImpl.OBJECT) {
                yamlFile.println(indent + "" + keyNode + ":");
                yamlFile.flush();
                convertNode(valueNode, level + 1);
            } else {
                yamlFile.println(
                        indent + "" + keyNode + ": " + (valueNode.getType() == ICodeNodeTypeImpl.STRING_LITERAL
                                ? ("\"" + valueNode + "\"")
                                : valueNode));
                yamlFile.flush();
            }
            return;
        }
        if (node.getType() == ICodeNodeTypeImpl.OBJECT) {
            List<ICodeNode> children = node.getChildren();
            if (!children.isEmpty()) {
                for (ICodeNode child : children) {
                    convertNode(child, level);
                }
            }
        }
        // Map<ICodeKey, Object> attributes = node.getAttributes();
        // if (!attributes.isEmpty()) {
        // for (Map.Entry<ICodeKey, Object> entry : attributes.entrySet()) {
        // yamlFile.println(indent + " " + entry.getKey() + ": " + entry.getValue());
        // yamlFile.flush();
        // }
        // }
    }
}
