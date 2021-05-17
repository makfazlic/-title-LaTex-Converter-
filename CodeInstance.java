/**
 * This class analyzes a node and convert it from String
 * to LateX expression.
 *
 * @author Roberto Ferrari
 * @version v0.1
 */
public class CodeInstance
{
    // instance variables - replace the example below with your own
    private String value;

    /**
     * Constructors for objects of class CodeInstance.
     */   
    public CodeInstance() {
    
   }
    
    public CodeInstance(String value) {
        this.value = value;
    }

    /**
     * Return the value of the object.
     *
     * @return The value of the object.
     */
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    // BASIS OPERATIONS SECTION
    
    /**
     * Return the addition from two nodes.
     *
     * @param other is a node of an expression.
     * @return the entire addition in LateX code.
     */
    public String makeAddition(CodeInstance firstNode, CodeInstance secondNode) {
        return "(" + firstNode.value + " + " + secondNode.value + ")";
    }
    
    /**
     * Return the subtraction from two nodes.
     *
     * @param firstNode is a String and represents the first node of the subraction.
     * @param secondNode is a String and represents the second node of the subtraction.
     * @return the entire subtraction in LateX code.
     */
    public String makeSubtraction(CodeInstance firstNode, CodeInstance secondNode) {
        return "(" + firstNode.value + " - " + secondNode.value + ")";
    }
    
    /**
     * Return the multiplication from two nodes.
     *
     * @param firstNode is a String and represents the first node of the multiplication.
     * @param secondNode is a String and represents the second node of the multiplication.
     * @return the entire multiplication in LateX code.
     */
    public String makeMultiplication(CodeInstance firstNode, CodeInstance secondNode) {
        return "(" + firstNode.value + " * " + secondNode.value + ")";
    }
    
    /**
     * Return the division from two nodes.
     *
     * @param firstNode is a String and represents the first node of the division.
     * @param secondNode is a String and represents the second node of the division.
     * @return the entire division in LateX code.
     */
    public String makeDivision(CodeInstance firstNode, CodeInstance secondNode) {
        return "/frac{" + firstNode.value + "}{" + secondNode.value + "}";
    }
    
    /**
     * Return the power from two nodes.
     *
     * @param node is a String and represents the node to be raised to a power.
     * @param exponent is a String and represents the the number which raises the node.
     * @return the entire exponentation in LateX code.
     */
    public String makePower(CodeInstance node, CodeInstance exponent) {
        return "(" + node.value + ")^{" + exponent.value + "}";
    }
    
    // COMPLEX OPERATIONS SECTION
    
    /**
     * Return the root of a nodes.
     *
     * @param grade is a String and represents the grade of the root.
     * @param node is a String and represents the node to be place on the root.
     * @return the entire division in LateX code.
     */
    public String makeRoot(CodeInstance node, CodeInstance grade) {
        return "/sqrt[" + grade.value + "]{" + node.value + "}";
    }
    
    /**
     * Return the limit of a nodes.
     *
     * @param grade is a String and represents the grade of the root.
     * @param node is a String and represents the node to be place on the root.
     * @return the entire division in LateX code.
     */
    public String makeLimit(CodeInstance node, CodeInstance variable, CodeInstance num) {
        return "/lim_{" + variable.value + "/" + num.value + "}{" + node.value + "}";
    }
}
