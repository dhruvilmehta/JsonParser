package src.intermediate.icodeimpl;

import src.intermediate.ICode;
import src.intermediate.ICodeNode;

public class ICodeImpl implements ICode {
    private ICodeNode root;

    @Override
    public ICodeNode setRoot(ICodeNode root) {
        this.root = root;
        return root;
    }

    @Override
    public ICodeNode getRoot() {
        return root;
    }

}
