package library;
import java.lang.ArithmeticException;
import java.time.*;
/** An error in the runtime duration for videos. */
public class InvalidRuntimeException extends ArithmeticException
{
    public InvalidRuntimeException ()
    {
        super();
    }
    public InvalidRuntimeException (String s)
    {
        super(s);
    }
    public InvalidRuntimeException (String title, int runtime)
    {
        String s = "\"" + title + "\"" + " has invalid runtime of " + runtime;
        throw new IndexOutOfBoundsException(s);
    }

}