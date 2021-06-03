/**
 * A Literal is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 * 
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class StringExpression extends Node {
    
    // instance variables
    private final String value;
    private final Node expression;
    
    /**
     * Create a new Literal node.
     * @param value the integer value this node evaluates to.
     * @param expression is the AST which is after the String.
     */
    public StringExpression(final String value, final Node expression) {
        super();
        this.value = value;
        this.expression = expression;
    }

    @Override
    // Returns Latex of Literal
    public String toLatex() {
        return "\\textit{" + value + "}: $" + expression.toLatex() + "$";
    }
    
}
