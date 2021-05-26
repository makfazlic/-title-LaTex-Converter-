rtil.ArrayList;

/**
 * Write a description of class Function here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Function
{
    // instance variables - replace the example below with your own
    private String sourcecode;

    /**
     * Constructor for objects of class Function
     */
    public Function(final String sourcecode)
    {
        setExpression(sourcecode);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public final void setExpression(final String sourcecode) {
        final Parser parser = new ArithParser();
        if (sourcecode != null) {
            final Node parsed = parser.parse(sourcecode);
            this.sourcecode = parsed.toString();
        }
    }
    
    public final String getExpression() {
        return sourcecode;
    }
        
}
