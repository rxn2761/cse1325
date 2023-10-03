package mdi;

import library.Library;
import library.Publication;
import library.Patron;
import library.Video;
import library.InvalidRuntimeException;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryManager
{
    private static final String regexForCheckingValidEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    private static String selection;
    private static String title;
    private static String author;
    private static int copyright;
    private static int runtime;

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Library L = new Library("Arlington Public Library");
        FileReader fr = new FileReader("InputData.txt");
        BufferedReader br = new BufferedReader(fr);
        String buffer;
        boolean isPublication = false;
        boolean isPatron = false;
        String [] temp;
        try
        {
            while ((buffer = br.readLine()) != null)
            {
                if (buffer.contains("Patrons"))
                {
                    isPatron = true;
                    isPublication = false;
                }
                else if (buffer.contains("Publications"))
                {
                    isPublication = true;
                    isPatron = false;
                }
                else
                {
                    if (isPatron)
                    {
                        temp = buffer.split(",");
                        Patron pa = new Patron(temp[0], temp[1]);
                        L.addPatron(pa);
                    }
                    else if (isPublication)
                    {
                        temp = buffer.split(",");
                        if (temp[0].contains("book"))
                        {
                            if (temp.length == 4) {
                                Publication p = new Publication(temp[1], temp[2], Integer.parseInt(temp[3]));
                                L.addPublication(p);
                            } else if (temp.length == 7) {
                                Publication p = new Publication(temp[1], temp[2], Integer.parseInt(temp[3]));
                                Patron pa = new Patron(temp[4], temp[5]);
                                p.setLoanedTo(pa);
                                String[] tempSplitString = temp[6].split("-");
                                LocalDate date = LocalDate.of(Integer.parseInt(tempSplitString[0]), Integer.parseInt(tempSplitString[1]), Integer.parseInt(tempSplitString[2]));
                                p.setDueDate(date);
                                L.addPublication(p);
                            }
                        }
                        else
                        {
                            if (temp.length == 5) {
                                Video v = new Video(temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                                L.addPublication(v);
                            } else if (temp.length == 8) {
                                Video v = new Video(temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                                Patron pa = new Patron(temp[5], temp[6]);
                                v.setLoanedTo(pa);
                                String[] tempSplitString = temp[7].split("-");
                                LocalDate date = LocalDate.of(Integer.parseInt(tempSplitString[0]), Integer.parseInt(tempSplitString[1]), Integer.parseInt(tempSplitString[21]));
                                v.setDueDate(date);
                                L.addPublication(v);
                            }
                        }

                    }
                }
            }
        }

        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Please check your InputData.txt file for valid data.");
        }

        boolean wantToExit = false;
        initialMenu(L);
        while (wantToExit == false)
        {
            if (selection.contains("1")) {
                L.toStringLibrary();
                initialMenu(L);
            } else if (selection.contains("2")) {
                newPublication(L);
                initialMenu(L);
            } else if (selection.contains("3")) {
                newVideo(L);
                initialMenu(L);
            } else if (selection.contains("4")) {
                newPatron(L);
                initialMenu(L);
            } else if (selection.contains("5")) {
                L.patronMenu();
                initialMenu(L);
            } else if (selection.contains("6")) {
                checkOutPublications(L);
                initialMenu(L);
            } else if (selection.contains("7")) {
                checkInPublications(L);
                initialMenu(L);
            } else if (selection.toLowerCase().contains("x")) {
                wantToExit = true;
                updateDataToInputDataFile(L);
            }
            else
            {
                System.out.println("You entered invalid selection. Valid selection is a number between 1 and 8 OR charactor x.");
                initialMenu(L);
            }

        }
    }

    public static void updateDataToInputDataFile(Library L) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(new File("InputData.txt"), false));
        ArrayList ListOfPatron = L.getListOfPatron();
        ArrayList ListOfPublication = L.getListOfPublication();

        br.write("Patrons" + "\n");
        int patronIndex = 0;
        while (ListOfPatron.size() > patronIndex)
        {
            Patron p = (Patron)ListOfPatron.get(patronIndex);
            br.write(p.getName() + "," + p.getEmail() + "\n");
            patronIndex++;

        }
        br.write("Publications"+ "\n");
        int publicationIndex = 0;
        while (ListOfPublication.size() > publicationIndex)
        {
            Publication p = (Publication)ListOfPublication.get(publicationIndex);
            br.write(p.PublicationObjectInfo() + "\n");
            publicationIndex++;
        }
        br.flush();

        br.close();
    }
    public static void initialMenu(Library L)
    {
        System.out.println("                                Main Menu");
        System.out.println("                    " + L.getName() + "\n");
        System.out.println("1) List Publications");
        System.out.println("2) Add New Publication");
        System.out.println("3) Add New Video");
        System.out.println("4) Add New Patron");
        System.out.println("5) List patrons");
        System.out.println("6) Check Out");
        System.out.println("7) Check in");
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\nSelection(Choose between 1-8 from the options above or select 'x' to exit): ");
        selection = sc.next();
        System.out.println("\n");
    }
    public static void newPublication(Library L)
    {
        //String title;
        //String author;
        //int copyright;
        Scanner sc = new Scanner(System.in);
        Publication p;
        try {
            System.out.print("Enter new Publication's title: ");
            title = sc.next();
            System.out.print("Enter new Publication's author: ");
            author = sc.next();
            System.out.print("Enter new Publication's copyright: ");
            copyright = sc.nextInt();
            p = new Publication(title, author, copyright);
            L.addPublication(p);
            System.out.println("Added Book \"" + title + "\" by " + author + ", copyright " + copyright);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("Invalid year for copyright"))
            {
                try {
                    System.out.println("If you want to add new Book, please enter valid year for copyright (year should be between 1900 to present year).");
                    System.out.print("Enter new Publication's copyright: ");
                    copyright = sc.nextInt();
                    p = new Publication(title, author, copyright);
                    L.addPublication(p);
                    System.out.println("Added Book \"" + title + "\" by " + author + ", copyright " + copyright);
                }
                catch (Exception e2)
                {
                    System.out.println(e.toString());
                    System.out.println("You entered invalid copyright for two times. The new book is NOT added to our system.");
                }
            }
        }
    }
    public static void newVideo(Library L)
    {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter new Video's title: ");
            title = sc.next();
            System.out.print("Enter new Video's author: ");
            author = sc.next();
            System.out.print("Enter new Video's copyright: ");
            copyright = sc.nextInt();
            System.out.print("Enter new Video's runtime (in minutes): ");
            runtime = sc.nextInt();
            Video v = new Video(title, author, copyright, runtime);
            L.addPublication(v);
            System.out.println("Added Video \"" + title + "\" by " + author + ", copyright " + copyright + ", runtime " + runtime + " minutes");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("Invalid year for copyright"))
            {
                try {
                    System.out.println("If you want to add new Video, please enter valid year for copyright (year should be between 1900 to present year).");
                    System.out.print("Enter new Video's copyright: ");
                    copyright = sc.nextInt();
                    System.out.print("Enter new Video's runtime (in minutes): ");
                    runtime = sc.nextInt();
                    Video v = new Video(title, author, copyright, runtime);
                    L.addPublication(v);
                    System.out.println("Added Video \"" + title + "\" by " + author + ", copyright " + copyright + ", runtime " + runtime + " minutes");
                }
                catch (Exception e2)
                {
                    if (e2.toString().contains("Invalid year for copyright")) {
                        System.out.println(e2.toString());
                        System.out.println("You entered invalid copyright twice. The new video is NOT added to our system.");
                    }
                    else if (e2.toString().contains("has invalid runtime"))
                    {
                        try {
                            System.out.println("If you want to add new Video, please enter valid time in minutes for runtime (runtime is integer but can't be negative number).");
                            System.out.print("Enter new Video's runtime (in minutes): ");
                            runtime = sc.nextInt();
                            Video v = new Video(title, author, copyright, runtime);
                            L.addPublication(v);
                            System.out.println("Added Video \"" + title + "\" by " + author + ", copyright " + copyright + ", runtime " + runtime + " minutes");
                        }
                        catch (Exception e3)
                        {
                            System.out.println(e3.toString());
                            if (e3.toString().contains("has invalid runtime"))
                                System.out.println("You entered invalid runtime twice. The new video is NOT added to our system.");
                        }
                    }
                }
            }
            else if (e.toString().contains("has invalid runtime"))
            {
                try {
                    System.out.println("If you want to add new Video, please enter valid time in minutes for runtime (runtime is integer but can't be negative number).");
                    System.out.print("Enter new Video's runtime (in minutes): ");
                    runtime = sc.nextInt();
                    Video v = new Video(title, author, copyright, runtime);
                    L.addPublication(v);
                    System.out.println("Added Video \"" + title + "\" by " + author + ", copyright " + copyright + ", runtime " + runtime + " minutes");
                }
                catch (Exception e2)
                {
                    System.out.println(e.toString());
                    if (e2.toString().contains("has invalid runtime"))
                        System.out.println("You entered invalid runtime twice. The new video is NOT added to our system.");
                }
            }
        }
    }
    public static void newPatron(Library L)
    {
        String name;
        String email;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new Patron's name (Here is the name format: FirstName Lastname): ");
        name = sc.nextLine();
        String[] testName = name.split(" ");
        if (!(testName.length == 2))
        {
            System.out.println("You just entered invalid name. Name should be in this format 'FirstName LastName'");
            System.out.print("Enter new Patron's name: ");
            name = sc.nextLine();
            testName = name.split(" ");
            if (!(testName.length == 2)) {
                System.out.println("You entered invalid name twice. We can't add this Patron to our system.");
                return;
            }
        }
        System.out.print("Enter new Patron's email: ");
        email = sc.next();
        Pattern pattern = Pattern.compile(regexForCheckingValidEmail);
        if (!(pattern.matcher(email).matches())) {
            System.out.println("You just entered invalid email.");
            System.out.print("Enter new Patron's email: ");
            email = sc.next();
            if (!(pattern.matcher(email).matches())) {
                System.out.println("You entered invalid email twice. We can't add this Patron to our system.");
                return;
            }
        }
        Patron pa = new Patron(name, email);
        L.addPatron(pa);
        System.out.println("Added Patron " + name + ", " + email);
    }
    public static void checkOutPublications(Library L)
    {
        try
        {
            L.toStringLibrary();
            Scanner sc = new Scanner(System.in);
            System.out.print("Which publication would you like to check out? ");
            int PublicationIndex = sc.nextInt();
            ArrayList l2 = L.getListOfPublication();
            Publication p = (Publication)l2.get(PublicationIndex);
            if (p.getLoanedTo() != null)
            {
                System.out.println("\n" + "Someone already loaned this book"+ "\n");
                return;
            }
            L.patronMenu();

            sc = new Scanner(System.in);
            System.out.print("Who are you? ");
            int PatronIndex = sc.nextInt();
            if (PatronIndex < 0 || PatronIndex > L.getListOfPatron().size() - 1) {
                System.out.println("Invalid number. It should be between 0 and " + (L.getListOfPatron().size() - 1) + ".");
                System.out.print("Who are you? ");
                PatronIndex = sc.nextInt();
                if (PatronIndex < 0 || PatronIndex > L.getListOfPatron().size() - 1) {
                    System.out.println("\n\nYou entered invalid number twice. Please choose another selection.");
                    return;
                }
            }
            ArrayList l1 = L.getListOfPatron();
            Patron pa = (Patron) l1.get(PatronIndex);
            L.checkOut(PublicationIndex, pa);
            System.out.println(p.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("IndexOutOfBoundsException"))
                System.out.println ("You entered Index number that is out of bound.");
        }
    }
    public static void checkInPublications(Library L)
    {
        try
        {
            L.patronMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Who are you? ");
            int PatronIndex = sc.nextInt();
            if (PatronIndex < 0 || PatronIndex > L.getListOfPatron().size() - 1)
            {
                System.out.println("Invalid number. It should be between 0 and " + (L.getListOfPatron().size() - 1) + ".");
                System.out.print("Who are you? ");
                PatronIndex = sc.nextInt();
                if (PatronIndex < 0 || PatronIndex > L.getListOfPatron().size() - 1)
                {
                    System.out.println("\n\nYou entered invalid number twice. Please choose another selection.");
                    return;
                }
            }

            ArrayList l1 = L.getListOfPatron();
            Patron pa = (Patron) l1.get(PatronIndex);
            String PatronName = pa.getName();

            L.toStringLibrary();
            sc = new Scanner(System.in);
            System.out.print("Which publication would you like to return? ");
            int PublicationIndex = sc.nextInt();
            ArrayList l2 = L.getListOfPublication();
            Publication p = (Publication) l2.get(PublicationIndex);
            String LoanedTo = "";
            if (p.getLoanedTo() != null) {
                LoanedTo = p.getLoanedTo().getName();
                if (PatronName.equals(LoanedTo))
                {
                    p.setLoanedTo(null);
                    p.setDueDate(null);
                    System.out.println("\nYou have successfully checked in your publication!");
                    System.out.println("This publication is now available to check out: " + p.toString() + "\n");
                }
                else
                {
                    System.out.println("\nSomeone else has checked out this publication but not you.\n");
                }
            } else
                System.out.println("\nNo one has checked out this publication.\n");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("IndexOutOfBoundsException"))
                System.out.println ("You entered Index number that is out of bound.");
        }
    }
}