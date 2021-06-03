/**
 * A Literal is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 * 
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class StringMessage extends Node {
    
    // instance variables
    private final String value;
    
    /**
     * Create a new Literal node.
     * @param value the integer value this node evaluates to
     */
    public StringMessage(final String value) {
        super();
        this.value = value;
    }

    @Override
    // Returns Latex of Literal
    public String toLatex() {
        return "\\]\\section{" + value +"}\\[";
    }
    
}
