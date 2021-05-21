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
            if (lexer.getCurrentToken().getText().equals("^")) {
                this.lexer.fetchNextToken();
                expressionNode = new Power(expressionNode, this.parseTerm());
                this.lexer.fetchNextToken();
            }
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
                    factorNode = this.rootParser(); // go through the expression to exctract the root
                    break;
                // limit case
                case "lim":
                    this.lexer.fetchNextToken();
                    factorNode = this.limitParser(); // go through the expression to exctract the limit
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

    public Node limitParser() {
        Node limitNode;
        final Node variable;
        final Node value;
        // check if the user used ":" after the limit
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();
            this.lexer.fetchNextToken();
            // limit variable
            variable = new Variable(lexer.getCurrentToken().getText());
        } else {
            // TODO
            // Exception wrong token
            variable = null;
        }
        this.lexer.fetchNextToken();
        // check if the user used "," between variable and value
        if (this.tokenAnalyzer(",")) {
            this.lexer.fetchNextToken();
            // limit variable
            value = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
            this.lexer.fetchNextToken();
        } else {
            // TODO
            // Exception wrong token
            value = null;
        }        
        this.lexer.fetchNextToken();
        // check if the user used ":" between variable declaration and the body of the limit
        if (this.tokenAnalyzer(":")) {
            //creation limit
            limitNode = new Limit(this.parseExpression(), variable, value);
        } else {
            // TODO
            // Exception wrong token
            limitNode = null;
        }

        this.lexer.fetchNextToken();
        return limitNode;
    }
    
    public Node rootParser() {
        Node rootNode;
        final Node grade;
        // check if the user used ":" after the root
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();
            grade = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
        } else {
            // TODO
            // Exception wrong token
            grade = null;
        }        
        this.lexer.fetchNextToken();
        // check if the user used ":" between the grade of the root and its body
        if (this.tokenAnalyzer(":")) {
            // creation root
            rootNode = new Root(grade, this.parseExpression());
        } else {
            // TODO
            // Exception wrong token
            rootNode = null;
        }
        
        this.lexer.fetchNextToken();
        return rootNode;
    }
    
    public boolean tokenAnalyzer(String expectedToken) {
        return this.lexer.getCurrentToken().getText().equals(expectedToken);
    }
}
