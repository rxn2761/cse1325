package mdi;

import library.Library;
import library.Publication;
import library.Patron;
import library.Video;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LibraryManager
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Library L = new Library("Arlington Public Library");
        FileReader fr = new FileReader("InputData.txt");
        BufferedReader br = new BufferedReader(fr);
        String buffer;
        boolean isPublication = false;
        boolean isVideo = false;
        boolean isPatron = false;
        String [] temp1;
        String [] temp2;
        while ((buffer = br.readLine()) != null)
        {
            if (buffer.contains("Publications")) {
                isPublication = true;
                isVideo = false;
                isPatron = false;
            }
            else if (buffer.contains("Videos"))
            {
                isVideo = true;
                isPatron = false;
                isPublication = false;

            }
            else if (buffer.contains("Patrons"))
            {
                isPatron = true;
                isPublication = false;
                isVideo = false;
            }
            else
            {
                if (isPublication)
                {
                    temp1 = buffer.split(",");
                    Publication p = new Publication(temp1[0],temp1[1],Integer.parseInt(temp1[2]));
                    L.addPublication(p);
                }
                else if (isVideo)
                {
                    temp1 = buffer.split(",");
                    Video v = new Video(temp1[0],temp1[1],Integer.parseInt(temp1[2]),Integer.parseInt(temp1[3]));
                    L.addPublication(v);
                }
                else if (isPatron)
                {
                    temp2 = buffer.split(",");
                    Patron pa = new Patron(temp2[0],temp2[1]);
                    L.addPatron(pa);
                }
            }
        }

        L.toStringLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Which book would you like to check out? ");
        int BookIndex = sc.nextInt();

        L.patronMenu();

        sc = new Scanner(System.in);
        System.out.print("Who are you? ");
        int PatronIndex = sc.nextInt();
        ArrayList l = L.getListOfPatron();
        Patron pa = (Patron)l.get(PatronIndex);

        L.checkOut(BookIndex, pa);

        L.toStringLibrary();
    }
}