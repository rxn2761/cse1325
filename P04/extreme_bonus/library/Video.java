package library;
import java.time.*;
/** A library video that can be checked out by patrons. */
public class Video extends Publication {
    private Duration runtime = null;
    public Video(String title, String author, int copyright, int runtime)
    {
        super(title, author, copyright);
        this.runtime = Duration.ofMinutes(runtime);
    }


    public String toString()
    {
        String returnString = "";
        if (super.getPatron() != null)
        {
            if (runtime.toMinutes() < 0)
                throw new InvalidRuntimeException(super.getTitle(), runtime.toMinutes());
            else
                returnString = "Video " + "\"" + super.getTitle() + "\"" + " by " + super.getAuthor() + ", copyright " + super.getCopyright() + ", runtime " + runtime.toMinutes() + " minutes"
                    + ": " + "loaned to " + super.getPatron().toStringPatron() + " until " + super.getDueDate();
        }
        else
        {
            if (runtime.toMinutes() < 0)
                throw new InvalidRuntimeException(super.getTitle(), runtime.toMinutes());
            else
                returnString = "Video " + "\"" + super.getTitle() + "\"" + " by " + super.getAuthor() + ", copyright " + super.getCopyright()+ ", runtime " + runtime.toMinutes() + " minutes";
        }
        return returnString;
    }
}
