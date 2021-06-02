
/**
 * Exception class which checks if the current token is correct.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class UnexpectedTokenException extends Exception
{
    /**
     * Constructor for objects of class UnexcpectedTokenException.
     * @param message is a String which represents the error message.
     */
    public UnexpectedTokenException(final String message)
    {
        super(message);
    }
}
