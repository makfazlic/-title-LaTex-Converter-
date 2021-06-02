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
        this.lexer.fetchNextToken();
        try {
            final Node expression = parseExpression();
            this.lexer.fetchNextToken();
            if (lexer.getCurrentToken().getText().equals("=")) {
                this.lexer.fetchNextToken();
                this.lexer.fetchNextToken();
                return new Equation(expression, this.parseExpression());
            } else {
                return expression;
            }
        } catch (UnexpectedTokenException ex) {
            return new Error(ex.getMessage());
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
     * @return a Node representing the expression.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    private Node parseExpression() throws UnexpectedTokenException {
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
     * @return a Node representing the term.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    private Node parseTerm() throws UnexpectedTokenException {
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
     * @return a Node representing the factor.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    private Node parseFactor() throws UnexpectedTokenException {
        Node factorNode;
        if (lexer.getCurrentToken().getType() == TokenType.LITERAL) {
            // literal case
            factorNode = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
            this.lexer.fetchNextToken();
        } else if (lexer.getCurrentToken().getType() == TokenType.IDENTIFIER) {
            // identifier case
            switch (lexer.getCurrentToken().getText()) {
                case "root":
                    this.lexer.fetchNextToken();
                    factorNode = this.rootParser(); // go through the expression to exctract root
                    break;
                case "lim":
                    this.lexer.fetchNextToken();
                    factorNode = this.limitParser(); // go through the expression to exctract limit
                    break;
                case "string":
                    this.lexer.fetchNextToken();
                    this.lexer.fetchNextToken();
                    factorNode = this.stringParser(); // case -> "String" or "String: Expression"   
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
    
    /**
     * Parse in case there's a limit in the expression.
     * @return a Node representing the limit.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node limitParser() throws UnexpectedTokenException {
        // set limit variable
        final Node variable = this.creationLimitVariable();
        this.lexer.fetchNextToken();
        // set limit value
        final Node value = this.creationLimitValue();
        this.lexer.fetchNextToken();
        // create the limit
        final Node limitNode = this.creationLimit(variable, value);
        // check if the user close the limit with a colon
        this.parseError(":", "You must close the limit operation with a colon");
        this.lexer.fetchNextToken();
        
        return limitNode;
    }
    
    /**
     * Parse in case there's a root in the expression.
     * @return a Node representing the limit.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node rootParser() throws UnexpectedTokenException {   
        // set root grade
        final Node grade = this.creationRootGrade();  
        this.lexer.fetchNextToken();
        // create the root
        final Node rootNode = this.creationRoot(grade);
        // check if the user close the root with a colon
        this.parseError(":", "You must close the root operation with a colon");
        this.lexer.fetchNextToken();
        
        return rootNode;
    }
    
    /**
     * It analyzes the expression if the user want to insert a String in the .tex file.
     * @return a Node with the String and if necessary the expression.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node stringParser() throws UnexpectedTokenException {
        String stringToInsert = "";
        while (lexer.getCurrentToken().getType() != TokenType.CLOSED_PAREN) {
            stringToInsert = stringToInsert + lexer.getCurrentToken().getText();
            this.lexer.fetchNextToken();
        }
        this.lexer.fetchNextToken();
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();
            return new StringExpression(stringToInsert, this.parseExpression());
        } else {
            return new StringMessage(stringToInsert);
        }
    }
    
    /**
     * It analyzes the current Token in the expression.
     * @param expectedToken is a String which represents the token that we excpect.
     * @return a boolean which is True if the current token is equal to the token 
     *          which we excpect.         
     */
    public boolean tokenAnalyzer(final String expectedToken) {
        return this.lexer.getCurrentToken().getText().equals(expectedToken);
    }
    
    /**
     * It creates the grade of the root in the expression.
     * @return a Node representing the grade of the root in LaTex.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node creationRootGrade() throws UnexpectedTokenException {
        // check if there is a colon after the word 'root'
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();     
            return this.parseExpression();
        } else {
            throw new UnexpectedTokenException("You must to start with colon after 'root' word");
        }
    }
    
    /**
     * It creates the root in the expression.
     * @param grade is a Node which represents the grade of the root.
     * @return a Node representing the root in LaTex.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node creationRoot(final Node grade) throws UnexpectedTokenException {
        // check if there is a colon between the grade and the body of the root
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();        
            return new Root(grade, this.parseExpression());
        } else {
            throw new UnexpectedTokenException("The body of the root and the grade "
                                                + "must be separated by a colon");
        }        
    }
    
    /**
     * It creates the variable of the limit in the expression.
     * @return a Node representing the variable of the limit in LaTex.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */    
    public Node creationLimitVariable() throws UnexpectedTokenException {
        // check if there is a colon after the word 'lim'
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();
            this.lexer.fetchNextToken();      
            return new Variable(lexer.getCurrentToken().getText());
        } else {
            throw new UnexpectedTokenException("You must to start with colon after 'lim' word");
        }
    }
    
    /**
     * It creates the value of the limit in the expression.
     * @return a Node representing the value of the limit in LaTex.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node creationLimitValue() throws UnexpectedTokenException {
        // check if there is a comma which separate the variable and the value
        if (this.tokenAnalyzer(",")) {
            this.lexer.fetchNextToken();
            return this.parseExpression();
        } else {
            throw new UnexpectedTokenException("Put a comma between the variable "
                                                + "and the variable value");
        }
    }
    
    /**
     * It creates the limit in the expression.
     * @param variable is a Node which represents the variable of the limit.
     * @param value is a Node which represents the value of the limit. 
     * @return a Node representing the limit in LaTex.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public Node creationLimit(final Node variable, final Node value) 
        throws UnexpectedTokenException {
        if (this.tokenAnalyzer(":")) {
            this.lexer.fetchNextToken();
            return new Limit(this.parseExpression(), variable, value);
        } else {
            throw new UnexpectedTokenException("The body of the limit and the variable section " 
                                                + "must be separated by a colon");
        }
    }
    
    /**
     * It checks if the user has written wrong the expression and update errorString with
     * a message which explain what's going wrong.
     * @param expectedToken is a String which represents the expected token.
     * @param errorMessage is a String which represents the message that the 
     *          user will see if there's an error.
     * @throws UnexpectedTokenException if there isn't the expected token.
     */
    public void parseError(final String expectedToken, final String errorMessage) 
        throws UnexpectedTokenException {
        if (!this.tokenAnalyzer(expectedToken)) {
            throw new UnexpectedTokenException(errorMessage);
        }
    }
}
