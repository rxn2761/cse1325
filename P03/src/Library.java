import java.util.*;
public class Library
{
    private String name;
    private ArrayList<Publication> list = new ArrayList<Publication>();

    Library (String name)
    {
        this.name = name;
    }

    public void addPublication(Publication p)
    {
        list.add(p);
    }

    void checkOut (int publicationIndex, String patron)
    {
        int numOfBooks = list.size();
        if (publicationIndex < 0)
            throw new IndexOutOfBoundsException("Cannot be a negative amount of books");
        else if (publicationIndex >= numOfBooks)
            throw new IndexOutOfBoundsException("Number of books cannot exceed the amount of books available in the Library");
        list.get(publicationIndex).checkIn(patron);
        list.get(publicationIndex).checkOut();
    }

    public void toStringLibrary()
    {
        System.out.println(this.name);
        int publicationIndex = 0;
                while (this.list.size() > publicationIndex)
                {
                    System.out.println(publicationIndex + ") " +this.list.get(publicationIndex).toString());
                    publicationIndex++;
                }
    }
}
