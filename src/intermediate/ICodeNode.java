package src.intermediate;

import java.util.Map;
import java.util.List;

public interface ICodeNode {
    public ICodeNodeType getType();

    public ICodeNode getParent();

    public ICodeNode addChild(ICodeNode node);

    public List<ICodeNode> getChildren();

    public void setAttribute(ICodeKey key, Object value);

    public Map<ICodeKey, Object> getAttributes();
}
