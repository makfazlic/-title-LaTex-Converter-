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
public class ArithParserTest{

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
        final String sourceCode = "root:(2):(2+2):*(4+4)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Multiplication(new Root(new Literal(2), new Addition(new Literal(2), new Literal(2))), new Addition(new Literal(4), new Literal(4)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void testDivision() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(root:(2):(2+2):/(4+4))";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Division(new Root(new Literal(2), new Addition(new Literal(2), new Literal(2))), new Addition(new Literal(4), new Literal(4)));
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
    public void testPower() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "(2*2)^(2)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Power(new Multiplication(new Literal(2), new Literal(2)), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testRoot() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "root:(2):(2+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Root(new Literal(2), new Addition(new Literal(2), new Literal(2)));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testLimit() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(x,2):(2+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Addition(new Literal(2), new Literal(2)), new Variable("x"), new Literal(2));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    
    @Test
    public void AdvancedTest() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(x,10):((12-2)/(4+4)):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Division(new Subtraction(new Literal(12), new Literal(2)), new Addition(new Literal(4), new Literal(4))), new Variable("x"), new Literal(10));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }

    @Test
    public void AdvancedTest2() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(x,10):(root:(2):(x+2):/(4+4)):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Division(new Root(new Literal(2), new Addition(new Variable("x"), new Literal(2))), new Addition(new Literal(4), new Literal(4))), new Variable("x"), new Literal(10));
        // assertion
        assertEquals(expectedRoot.toString(), actualRoot.toString());
    }
    
    @Test
    public void testRootError1() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "root(2:(x+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Division(new Root(new Literal(2), new Addition(new Variable("x"), new Literal(2))), new Addition(new Literal(4), new Literal(4))), new Variable("x"), new Literal(10));
        // assertion
        assertEquals("You must to start with colon after 'root' word", actualRoot.toString());
    }
    
    @Test
    public void testRootError2() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "root:2,(x+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Division(new Root(new Literal(2), new Addition(new Variable("x"), new Literal(2))), new Addition(new Literal(4), new Literal(4))), new Variable("x"), new Literal(10));
        // assertion
        assertEquals("The body of the root and the grade must be separated by a colon", actualRoot.toString());
    }
    
    @Test
    public void testRootError3() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "root:(2):(x+2))";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Division(new Root(new Literal(2), new Addition(new Variable("x"), new Literal(2))), new Addition(new Literal(4), new Literal(4))), new Variable("x"), new Literal(10));
        // assertion
        assertEquals("You must close the root operation with a colon", actualRoot.toString());
    }
    
    @Test
    public void testLimitError1() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim,(2,2):(2+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Addition(new Literal(2), new Literal(2)), new Literal(2), new Literal(2));
        // assertion
        assertEquals("You must to start with colon after 'lim' word", actualRoot.toString());
    }
    
    @Test
    public void testLimitError2() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(2-2):(2+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Addition(new Literal(2), new Literal(2)), new Literal(2), new Literal(2));
        // assertion
        assertEquals("Put a comma between the variable and the variable value", actualRoot.toString());
    }
    
    @Test
    public void testLimitError3() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(2,2),(2+2):";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Addition(new Literal(2), new Literal(2)), new Literal(2), new Literal(2));
        // assertion
        assertEquals("The body of the limit and the variable section must be separated by a colon", actualRoot.toString());
    }
    
    @Test
    public void testLimitError4() {
        // setup
        final Parser parser = new ArithParser();
        // test input
        final String sourceCode = "lim:(2,2):(2+2)";
        // code under test
        final Node actualRoot = parser.parse(sourceCode);
        // expected tree
        final Node expectedRoot = new Limit(new Addition(new Literal(2), new Literal(2)), new Literal(2), new Literal(2));
        // assertion
        assertEquals("You must close the limit operation with a colon", actualRoot.toString());
    }
}
