import java.time.LocalDate;
public class Publication
{
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo = null;
    private LocalDate dueDate = null;

    Publication (String title, String author, int copyright)
    {
        this.title = title;
        this.author = author;
        this.copyright = copyright;

        LocalDate currentDateTime = LocalDate.now();
        int currentYear = currentDateTime.getYear();

        if (copyright < 1900 || copyright > currentYear)
            throw new IllegalArgumentException("Invalid year for copyright");
    }

    void checkOut()
    {
        this.loanedTo = loanedTo;
        LocalDate currentDateTime = LocalDate.now();
        this.dueDate = currentDateTime.plusDays(14);
    }

    void checkIn(Patron patron)
    {
        this.loanedTo = patron;
    }

    public String toString()
    {
        String returnString = "";
        if (loanedTo != null)
        {
            returnString = "\"" + this.title + "\"" + " by " + this.author + ", copyright " + this.copyright
            + ": " + "loaned to " + this.loanedTo.toStringPatron() + " until " + this.dueDate;
        }
        else
        {
            returnString = "\"" + this.title + "\"" + " by " + this.author + ", copyright " + this.copyright;
        }
        return returnString;
    }
}