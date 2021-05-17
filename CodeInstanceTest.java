import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The tests of all CodeInstanceMethod.
 *
 * @author  Roberto Ferrari
 * @version v0.1
 */
public class CodeInstanceTest
{
    @Test
    public void testMakeAddition() {
        CodeInstance node1 = new CodeInstance("5");
        CodeInstance node2 = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeAddition(node1, node2));
        assertEquals("(5 + 6)", add.getValue());
    }
    
    @Test
    public void testMakeSubtraction() {
        CodeInstance node1 = new CodeInstance("5");
        CodeInstance node2 = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeSubtraction(node1, node2));
        assertEquals("(5 - 6)", add.getValue());
    }
    
    @Test
    public void testMakeMultiplication() {
        CodeInstance node1 = new CodeInstance("5");
        CodeInstance node2 = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeMultiplication(node1, node2));
        assertEquals("(5 * 6)", add.getValue());
    }
    
    @Test
    public void testMakeDivision() {
        CodeInstance node1 = new CodeInstance("5");
        CodeInstance node2 = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeDivision(node1, node2));
        assertEquals("/frac{5}{6}", add.getValue());
    }
    
    @Test
    public void testMakePower() {
        CodeInstance node1 = new CodeInstance("5 + 6");
        CodeInstance node2 = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makePower(node1, node2));
        assertEquals("(5 + 6)^{6}", add.getValue());
    }
    
    @Test
    public void testMakeRoot() {
        CodeInstance grade = new CodeInstance("5");
        CodeInstance node = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeRoot(node, grade));
        assertEquals("/sqrt[5]{6}", add.getValue());
    }
    
    @Test
    public void testMakeLimit() {
        CodeInstance variable = new CodeInstance("x");
        CodeInstance num = new CodeInstance("0");
        CodeInstance node = new CodeInstance("6");
        CodeInstance add = new CodeInstance();
        add.setValue(add.makeLimit(node, variable, num));
        assertEquals("/lim_{x/0}{6}", add.getValue());
    }
}
