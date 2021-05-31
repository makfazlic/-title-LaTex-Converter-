import java.util.ArrayList;

/**
 * Function.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Function
{
    // instance variables - replace the example below with your own
    private String sourcecode;

    /**
     * Constructor for objects of class Function.
     * 
     * @param sourcecode is a String which represents the math expression.
     */
    public Function(final String sourcecode)
    {
        setExpression(sourcecode);
    }

    /**
     * setExpression.
     *
     * @param sourcecode is a String which represents the math expression.
     */
    public final void setExpression(final String sourcecode) {
        final Parser parser = new ArithParser();
        if (sourcecode != null) {
            final Node parsed = parser.parse(sourcecode);
            this.sourcecode = parsed.toString();
        }
    }
    
    /**
     * it returns the expression.
     *
     * @return a String with the math expression.
     */
    public final String getExpression() {
        return sourcecode;
    }
        
}
