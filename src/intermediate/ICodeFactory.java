package src.intermediate;

import src.intermediate.icodeimpl.ICodeImpl;
import src.intermediate.icodeimpl.ICodeNodeImpl;

public class ICodeFactory {
    public static ICodeNode createICodeNode(ICodeNodeType type) {
        return new ICodeNodeImpl(type);
    }

    public static ICode createICode() {
        return new ICodeImpl();
    }
}
