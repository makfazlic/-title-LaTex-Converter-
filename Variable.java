
/**
 * It creates a variable prototype.
 *
 * @author Roberto Ferrari
 * @version v0.1
 */
public class Variable extends Node
{
    // instance variables - replace the example below with your own
    private final String name;

    /**
     * Constructor for objects of class Variable.
     * @param name is a String which represents the variable's name.
     */
    public Variable(final String name)
    {
        super();
        this.name = name;
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
