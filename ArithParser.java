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
    private String errorString;

    
    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     */
    public Node parse(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        this.errorString = "";
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        Node expression = parseExpression();
        // if the errorString is empty there aren't errors
        if (this.errorString.isEmpty()) {
            return expression;
        } else {
            return new Error(this.errorString);
        }
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
        // set limit variable
        final Node variable = this.creationLimitVariable();
        this.lexer.fetchNextToken();
        // set limit value
        final Node value = this.creationLimitValue();
        this.lexer.fetchNextToken();
        // create the limit
        final Node limitNode = this.creationLimit(variable, value);
        // check if the user close the limit with a colon
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {     
            this.errorString = "You must close the limit operation with a colon";
        }
        this.lexer.fetchNextToken();
        return limitNode;
    }
    
    public Node rootParser() {   
        // set root grade
        final Node grade = this.creationRootGrade();  
        this.lexer.fetchNextToken();
        // create the root
        final Node rootNode = this.creationRoot(grade);
        // check if the user close the root with a colon
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {
            this.errorString = "You must close the root operation with a colon";
        }
        this.lexer.fetchNextToken();
        return rootNode;
    }
    
    public boolean tokenAnalyzer(String expectedToken) {
        return this.lexer.getCurrentToken().getText().equals(expectedToken);
    }
    
    public Node creationRootGrade() {
        // check if there is a colon after the word 'root'
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {
            this.errorString = "You must to start with colon after 'root' word";
        }
        this.lexer.fetchNextToken();
        return this.parseExpression();
    }
    
    public Node creationRoot(Node grade) {
        // check if there is a colon between the grade and the body of the root
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {
            this.errorString = "The body of the root and the grade must be separated by a colon";
        }
        this.lexer.fetchNextToken();
        return new Root(grade, this.parseExpression());
    }
    
    public Node creationLimitVariable() {
        // check if there is a colon after the word 'lim'
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {
            this.errorString = "You must to start with colon after 'lim' word";
        }
        this.lexer.fetchNextToken();
        this.lexer.fetchNextToken();
        return new Variable(lexer.getCurrentToken().getText());
    }
    
    public Node creationLimitValue() {
        // check if there is a comma which separate the variable and the value
        if (!this.tokenAnalyzer(",") && this.errorString.isEmpty()) {
            this.errorString = "Put a comma between the variable and the variable value";
        }
        this.lexer.fetchNextToken();        
        return this.parseExpression();
    }
    
    public Node creationLimit(Node variable, Node value) {
        // check if there is a colon which separate the variable section and the body of the limit
        if (!this.tokenAnalyzer(":") && this.errorString.isEmpty()) {
            this.errorString = "The body of the limit and the variable section must be separated by a colon";
        }
        this.lexer.fetchNextToken();
        return new Limit(this.parseExpression(), variable, value);
    }
}
