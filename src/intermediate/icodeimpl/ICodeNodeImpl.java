package src.intermediate.icodeimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.intermediate.ICodeKey;
import src.intermediate.ICodeNode;
import src.intermediate.ICodeNodeType;

public class ICodeNodeImpl extends HashMap<ICodeKey, Object> implements ICodeNode {
    private ICodeNodeType type;
    private List<ICodeNode> children;
    private ICodeNode parent;

    public ICodeNodeImpl(ICodeNodeType type) {
        this.type = type;
        this.parent = null;
        children = new ArrayList<>();
    }

    @Override
    public ICodeNodeType getType() {
        return type;
    }

    @Override
    public ICodeNode getParent() {
        return parent;
    }

    @Override
    public ICodeNode addChild(ICodeNode node) {
        if (node != null) {
            children.add(node);
            ((ICodeNodeImpl) node).parent = this;
        }

        return node;
    }

    @Override
    public List<ICodeNode> getChildren() {
        return children;
    }

    public void setAttribute(ICodeKey key, Object value) {
        put(key, value);
    }

    public HashMap<ICodeKey, Object> getAttributes() {
        return new HashMap<>(this);
    }
}
