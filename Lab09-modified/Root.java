/**
 * Write a description of class Root here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Root extends Node
{
    // instance variables - replace the example below with your own
    private final Node grade;
    private final Node child;

    /**
     * Constructor for objects of class Power.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Root(final Node grade, final Node child)
    {
        super();
        this.grade = grade;
        this.child = child;       
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
 
    @Override
    public String toString() {
        return "(/sqrt[" + grade.toString() + "]{" + child.toString() + "})";
    }
}