package library;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/** Models a library that contains multiple publications. */
public class Library
{
    private String name;
    private ArrayList<Publication> ListOfPublication;
    private ArrayList<Patron> ListOfPatrons;

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
        this.ListOfPublication = new ArrayList<Publication>();
        this.ListOfPatrons = new ArrayList<Patron>();
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

    public void checkOut (int publicationIndex, int patronIndex)
    {
        try {
            ListOfPublication.get(publicationIndex).checkOut(ListOfPatrons.get(patronIndex));
        } catch(Exception e) {
            System.err.println("\nUnable to check out publication #" + publicationIndex
                    + ": " + e.getMessage() + "\n");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n\n");
        for (int i = 0; i < ListOfPublication.size(); ++i)
            sb.append("" + i + ") " + ListOfPublication.get(i).toString() + "\n");
        return sb.toString();
    }
    public void patronMenu()
    {
        System.out.println("Patrons");
        int patronIndex = 0;
            while (this.ListOfPatrons.size() > patronIndex)
             {
                 System.out.println(patronIndex + ") " + this.ListOfPatrons.get(patronIndex).toString());
                 patronIndex++;
             }
    }
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + "\n");
        bw.write("Patrons" + "\n");
        int patronIndex = 0;
        while (this.ListOfPatrons.size() > patronIndex)
        {
            Patron p = ListOfPatrons.get(patronIndex);
            p.save(bw);
            patronIndex++;
        }
        bw.write("Publications" + "\n");
        int publicationIndex = 0;
        while (ListOfPublication.size() > publicationIndex)
        {
            Publication pu = ListOfPublication.get(publicationIndex);
            pu.save(bw);
            publicationIndex++;
        }
        bw.flush();
        bw.close();
    }
    public Library(BufferedReader br) throws IOException
    {
        this.ListOfPublication = new ArrayList<Publication>();
        this.ListOfPatrons = new ArrayList<Patron>();
        String buffer;
        try
        {
            buffer = br.readLine();
            if (buffer.contains("<LIBRARY NAME>"))
                this.name = br.readLine();
            Boolean IsThereMorePatron = false;
            buffer = br.readLine();
            if (buffer.contains("<PATRONS>"))
                IsThereMorePatron = true;
            while (IsThereMorePatron)
            {
                buffer = br.readLine();
                if (buffer.equals("<PATRON>"))
                {
                    Patron p = new Patron;
                    p.save();ArrayList<Patron>
                }
            }
        }
    }
}
