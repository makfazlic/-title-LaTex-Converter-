/**
 * An abstract syntax tree (AST) node.
 */
public class Node {
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    public Type getType() {
        // to be implemented in subclasses
        return Type.INVALID;
    }

    /**
     * Decompile this node into a string.
     * Note that the resulting string may have
     * extra parentheses.
     * @return a String representation of this AST
     */
    public String toString() {
        // to be implemented in subclasses
        return "?";
    }
    
}
