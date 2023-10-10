package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
    public Publication(BufferedReader br) throws IOException
    {
        title = br.readLine();
        author = br.readLine();
        copyright = Integer.parseInt(br.readLine());
    }

    public void checkOut(Patron p)
    {
        this.loanedTo = p;
        LocalDate currentDateTime = LocalDate.now();
        this.dueDate = currentDateTime.plusDays(14);
    }

    public void checkOut(Patron p,String dueDate)
    {
        this.loanedTo = p;
        String[] tempSplitString = dueDate.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(tempSplitString[0]), Integer.parseInt(tempSplitString[1]), Integer.parseInt(tempSplitString[2]));
        this.dueDate = date;
    }

    public void checkIn()
    {
        this.loanedTo = null;
        this.dueDate = null;
    }

    protected String toStringBuilder(String pre, String mid)
    {
        return pre + " \"" + title + "\" by " + author + ", copyright " + copyright
                + mid
                + ((loanedTo != null) ? ": loaned to " + loanedTo + " until " + dueDate
                : "");
    }

    @Override
    public String toString()
    {
        return toStringBuilder("Book", "");
    }


    protected String toStringBuilderForSave(String pre, String mid)
    {
        return pre + "," + title + "," + author + "," + copyright
                + mid
                + ((loanedTo != null) ? "," + loanedTo + "," + dueDate
                : "");
    }

    public void save(BufferedWriter bw) throws IOException
    {
        String ObjectInfo = toStringBuilderForSave("Book", "");
        String[] temp = ObjectInfo.split(",");
        if (temp.length == 4)
        {
            bw.write("<BOOK CHECKED IN>" + "\n");
            bw.write(temp[1] + "\n");
            bw.write(temp[2] + "\n");
            bw.write(temp[3] + "\n");
        }
        else
        {
            bw.write("<BOOK CHECKED OUT>" + "\n");
            bw.write(temp[1] + "\n");
            bw.write(temp[2] + "\n");
            bw.write(temp[3] + "\n");

            String[] split = temp[4].split("\\(");
            bw.write(split[0] + "\n");
            bw.write(split[1].replace(")", "") + "\n");

            bw.write(temp[5] + "\n");
        }
    }
}