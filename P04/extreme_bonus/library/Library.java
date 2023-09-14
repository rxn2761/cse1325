package library;

import java.util.*;
/** Models a library that contains multiple publications. */
public class Library
{
    private String name;
    private ArrayList<Publication> ListOfBooks = new ArrayList<Publication>();
    private ArrayList<Patron> ListOfPatrons = new ArrayList<Patron>();

    public ArrayList getListOfPatron ()
    {
        return ListOfPatrons;
    }

    public Library (String name)
    {
        this.name = name;
    }

    public void addPublication(Publication p)
    {
        ListOfBooks.add(p);
    }

    public void addPatron(Patron p)
    {
        ListOfPatrons.add(p);
    }

    public void checkOut (int publicationIndex, Patron patron)
    {
        int numOfBooks = ListOfBooks.size();
        if (publicationIndex < 0)
            throw new IndexOutOfBoundsException("Cannot be a negative amount of books");
        else if (publicationIndex >= numOfBooks)
            throw new IndexOutOfBoundsException("Number of books cannot exceed the amount of books available in the Library");
        ListOfBooks.get(publicationIndex).checkIn(patron);
        ListOfBooks.get(publicationIndex).checkOut();
    }

    public void toStringLibrary()
    {
        System.out.println(this.name);
        int publicationIndex = 0;
                while (this.ListOfBooks.size() > publicationIndex)
                {
                    System.out.println(publicationIndex + ") " +this.ListOfBooks.get(publicationIndex).toString());
                    publicationIndex++;
                }
    }

    public void patronMenu()
    {
        System.out.println("Patrons");
        int patronIndex = 0;
            while (this.ListOfPatrons.size() > patronIndex)
             {
                 System.out.println(patronIndex + ") " + this.ListOfPatrons.get(patronIndex).toStringPatron());
                 patronIndex++;
             }
    }
}
