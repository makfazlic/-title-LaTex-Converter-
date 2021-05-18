/**
 * A Parser for our Arith language
 * (a simple language of arithmetic expressions).
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal | 
 *                  Identifier| 
 *                  "(" EXPRESSION ")"
 * </code>
 */
public final class ArithParser implements Parser {
    
    private LexicalAnalyzer lexer;

    
    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     */
    public Node parse(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        return parseExpression();
    }
    
    /**
     * Parse an expression.
     * This assumes the lexer already points to the first token of this expression.
     * 
     * <p>EBNF:
     * <code>
     * EXPRESSION ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
     * </code>
     * 
     * @return a Node representing the expression
     */
    private Node parseExpression() {
        Node expressionNode;
        //check if first node has before "-" or "+"
        if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
            // has "+" before first Term and skip the sign
            this.lexer.fetchNextToken();
            expressionNode = new Negation(this.parseTerm());           
        } else if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
            // has "+" before first Term and skip the sign
            this.lexer.fetchNextToken();
            expressionNode = this.parseTerm();
        } else {
            // when literal has not sign
            expressionNode = this.parseTerm();
        }
        
        // do a loop for all TERM in the EXPRESSION
        while (lexer.getCurrentToken().getType() == TokenType.PLUS 
                || lexer.getCurrentToken().getType() == TokenType.MINUS) {
            if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
                // do addition if before TERM there's "+"
                expressionNode = new Addition(expressionNode, this.parseTerm());
            } else if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
                // do subtraction if before TERM there's "-"
                expressionNode = new Subtraction(expressionNode, this.parseTerm());
            }
            this.lexer.fetchNextToken();
            if (lexer.getCurrentToken().getText().equals("^")) {
                this.lexer.fetchNextToken();
                expressionNode = new Power(expressionNode, this.parseTerm());
            }
        }
        return expressionNode;
    }
    
    /**
     * Parse a term.
     * This assumes the lexer already points to the first token of this term.
     * 
     * <p>EBNF:
     * <code>
     * TERM ::= FACTOR { ( "*" | "/" ) FACTOR }
     * </code>
     * 
     * @return a Node representing the term
     */
    private Node parseTerm() {
        Node termNode = this.parseFactor();
        // do a loop for the case which has multiplication or division
        while (lexer.getCurrentToken().getType() == TokenType.STAR 
                || lexer.getCurrentToken().getType() == TokenType.SLASH) {     
            if (lexer.getCurrentToken().getType() == TokenType.STAR) {
                // do addition if before TERM there's "+"
                termNode = new Multiplication(termNode, this.parseFactor());
            } else if (lexer.getCurrentToken().getType() == TokenType.SLASH) {
                // do subtraction if before TERM there's "-"
                termNode = new Division(termNode, this.parseFactor());
            }
            this.lexer.fetchNextToken();
            if (lexer.getCurrentToken().getText().equals("^")) {
                this.lexer.fetchNextToken();
                termNode = new Power(termNode, this.parseFactor());
            }
        }
        return termNode;
    }
    
    /**
     * Parse a factor.
     * This assumes the lexer already points to the first token of this factor.
     * 
     * <p>EBNF:
     * <code>
     * FACTOR ::=  
     *          Literal | 
     *          Identifier | 
     *          Root |
     *          Limit |
     *          "(" EXPRESSION ")"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseFactor() {
        Node factorNode;
        if (lexer.getCurrentToken().getType() == TokenType.LITERAL) {
            // literal case
            factorNode = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
            this.lexer.fetchNextToken();
        } else if (lexer.getCurrentToken().getType() == TokenType.IDENTIFIER) {
            // identifier case
            switch (lexer.getCurrentToken().getText()) {
                // root case
                case "root":
                    this.lexer.fetchNextToken();
                    this.lexer.fetchNextToken();
                    // root grade
                    final Node grade = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
                    this.lexer.fetchNextToken();
                    // creation root
                    factorNode = new Root(grade, this.parseExpression());
                    this.lexer.fetchNextToken();
                    break;
                // limit case
                case "lim":
                    this.lexer.fetchNextToken();    //
                    this.lexer.fetchNextToken();    // go until variable
                    this.lexer.fetchNextToken();    //
                    // limit variable
                    final Node variable = new Variable(lexer.getCurrentToken().getText());
                    this.lexer.fetchNextToken();    //
                    this.lexer.fetchNextToken();    // go until value
                    this.lexer.fetchNextToken();    //
                    // variable value
                    final Node value = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
                    this.lexer.fetchNextToken();
                    //creation limit
                    factorNode = new Limit(this.parseExpression(), variable, value);
                    this.lexer.fetchNextToken();
                    break;
                // variable case
                default:
                    factorNode = new Variable(lexer.getCurrentToken().getText());
                    this.lexer.fetchNextToken();
                    break;
            }
        } else {
            // expression case
            this.lexer.fetchNextToken();
            factorNode = this.parseExpression();
        }
        return factorNode;
    }

}
