import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class will test some aspects of the rules
 * of the Arith language.
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal | 
 *                  Identifier| 
 *                  "(" EXPRESSION ")"
 * </code>
 */
public class ArithParserTest {

    @Test
    public void testLiteral() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testVariable() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "x";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Variable("x");
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testNegation() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "-11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Negation(new Literal(11));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
        
    @Test
    public void testUnaryPlus() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "+11";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(11);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
       
    @Test
    public void testAddition() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12+2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Addition(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testSubtraction() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12-2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Subtraction(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testMultiplication() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12*2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Multiplication(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "12/2";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Literal(12), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
 
    @Test
    public void testParentheses() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(12)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testPower1() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "((5+(2^2))+(5+5))";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals("((5+(2^{2}))+(5+5))", actualRoot.toString());
    }
    
    @Test
    public void testPower2() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "((5+2)^2)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals("((5+2)^{2})", actualRoot.toString());
    }
    
    @Test
    public void testRoot() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(root:2:(2+2):)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals("(/sqrt[2]{(2+2)})", actualRoot.toString());
    }
    
    @Test
    public void testLimit() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(lim:(x->10):(x+2):)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Literal(12);
        // assertion
        assertEquals("(/lim_{x/to10}{(x+2)})", actualRoot.toString());
    }
    
    @Test
    public void myTest() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "root:2:(2+2):/(4+4)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Subtraction(new Literal(12), new Literal(2)), new Addition(new Literal(23), new Literal(12)));
        // assertion
        assertEquals("(/frac{(/sqrt[2]{(2+2)})}{(4+4)})", actualRoot.toString());
    }
    
    @Test
    public void myAdvancedTest() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(lim:(x->10):(root:2:(x+2):/(4+4)):)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Subtraction(new Literal(12), new Literal(2)), new Addition(new Literal(23), new Literal(12)));
        // assertion
        assertEquals("(/lim_{x/to10}{(/frac{(/sqrt[2]{(x+2)})}{(4+4)})})", actualRoot.toString());
    }
    
    @Test
    public void myAdvancedTest2() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(lim:(x->10):((5+(2^2))+(5+5)):)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Subtraction(new Literal(12), new Literal(2)), new Addition(new Literal(23), new Literal(12)));
        // assertion
        assertEquals("(/lim_{x/to10}{((5+(2^{2}))+(5+5))})", actualRoot.toString());
    }
}
