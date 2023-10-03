package library;

import java.time.LocalDate;
/** A library resource that can be checked out by patrons. */
public class Publication
{
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo = null;
    private LocalDate dueDate = null;

    public Publication (String title, String author, int copyright)
    {
        this.title = title;
        this.author = author;
        this.copyright = copyright;

        LocalDate currentDateTime = LocalDate.now();
        int currentYear = currentDateTime.getYear();

        if (copyright < 1900 || copyright > currentYear)
            throw new IllegalArgumentException("Invalid year for copyright");
    }

    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public int getCopyright()
    {
        return copyright;
    }
    public void setLoanedTo (Patron p)
    {
        this.loanedTo = p;
    }
    public void setDueDate(LocalDate l)
    {
        this.dueDate = l;
    }
    public Patron getLoanedTo ()
    {
        return loanedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
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
            returnString = "Book " + "\"" + this.title + "\"" + " by " + this.author + ", copyright " + this.copyright
            + ": " + "loaned to " + this.loanedTo.toStringPatron() + " until " + this.dueDate;
        }
        else
        {
            returnString = "Book " + "\"" + this.title + "\"" + " by " + this.author + ", copyright " + this.copyright;
        }
        return returnString;
    }

    public String PublicationObjectInfo()
    {
        String returnString = "";
        if (loanedTo != null)
        {
            returnString = "book" + "," + this.title + "," + this.author + "," + this.copyright + "," + this.loanedTo.getName() + "," + this.loanedTo.getEmail() + "," + this.dueDate;
        }
        else
        {
            returnString = "book" + "," + this.title + "," + this.author + ","  + this.copyright;
        }
        return returnString;
    }
}