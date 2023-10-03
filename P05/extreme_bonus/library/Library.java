package library;

import java.util.*;
/** Models a library that contains multiple publications. */
public class Library
{
    private String name;
    private ArrayList<Publication> ListOfPublication = new ArrayList<Publication>();
    private ArrayList<Patron> ListOfPatrons = new ArrayList<Patron>();

    public ArrayList getListOfPublication ()
    {
        return ListOfPublication;
    }
    public ArrayList getListOfPatron ()
    {
        return ListOfPatrons;
    }

    public Library (String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addPublication(Publication p)
    {
        ListOfPublication.add(p);
    }

    public void addPatron(Patron p)
    {
        ListOfPatrons.add(p);
    }

    public void checkOut (int publicationIndex, Patron patron)
    {
        int numOfBooks = ListOfPublication.size();
        if (publicationIndex < 0)
            throw new IndexOutOfBoundsException("The Index from the list of book can't be negative.");
        else if (publicationIndex >= numOfBooks)
            throw new IndexOutOfBoundsException("The Index from the list of book is out of bound.");
        ListOfPublication.get(publicationIndex).checkIn(patron);
        ListOfPublication.get(publicationIndex).checkOut();
    }

    public void toStringLibrary()
    {
        System.out.println(this.name);
        int publicationIndex = 0;
                while (this.ListOfPublication.size() > publicationIndex)
                {
                    System.out.println(publicationIndex + ") " +this.ListOfPublication.get(publicationIndex).toString());
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
