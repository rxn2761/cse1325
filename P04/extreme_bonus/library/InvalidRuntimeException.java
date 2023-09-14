package library;
import java.lang.ArithmeticException;
import java.time.*;
/** A error in the runtime duration for videos. */
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
    public InvalidRuntimeException (String title, Long runtime)
    {
        String s = "\"" + title + "\"" + " has invalid runtime " + runtime.toString();
        throw new IndexOutOfBoundsException(s);
    }

}