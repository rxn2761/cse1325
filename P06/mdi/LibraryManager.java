package mdi;

import library.Library;
import library.Publication;
import library.Patron;
import library.Video;
import library.InvalidRuntimeException;
import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.Console;
import java.io.InputStreamReader;

import java.util.regex.Pattern;

public class LibraryManager
{
    public static void main(String[] args) throws InvalidRuntimeException, IOException
    {
        boolean wantToExit = false;
        firstMenu();
        while (wantToExit == false) {
            if (selection.contains("1"))
            {
                openLibrary();
                break;
            }
            else if (selection.contains("2"))
            {
                wantToExit = true;
                java.lang.System.exit(0);
            }
            else
            {
                System.out.println("You entered invalid selection. Valid selection is a number between 1 and 2.");
                firstMenu();
            }
        }
        Menu();
        while (wantToExit == false)
        {
            if (selection.contains("0")) {
                wantToExit = true;
                Scanner sc = new Scanner(System.in);
                System.out.print("Do you want to save data before exit (type Y or N)? ");
                String yesOrNo = sc.nextLine();
                if (yesOrNo.toUpperCase().contains("Y"))
                    saveLibrary();
            }
            else if (selection.contains("1")) {
                listOfPublications();
                Menu();
            } else if (selection.contains("2")) {
                newPublication();
                Menu();
            } else if (selection.contains("3")) {
                checkOutPublications();
                Menu();
            } else if (selection.contains("4")) {
                checkInPublications();
                Menu();
            } else if (selection.contains("5")) {
                listOfPatrons();
                Menu();
            } else if (selection.contains("6")) {
                newPatron();
                Menu();
            } else if (selection.contains("7")) {
                saveLibrary();
                Menu();
            }
            else
            {
                System.out.println("You entered invalid selection. Valid selection is a number between 1 and 7 OR 0 to exit.");
                Menu();
            }

        }
    }

    public static void firstMenu()
    {
        System.out.println('\n' + "Options" + '\n');
        System.out.println("1) Open library");
        System.out.println("2) Exit");

        Scanner sc = new Scanner(System.in);
        System.out.print("\n\nSelection(Choose 1 to load library data to the system OR choose 2 to exit): ");
        selection = sc.next();
        System.out.println("\n");
    }
    public static void Menu()
    {
        System.out.println("\n\n=========");
        System.out.println("Main Menu");
        System.out.println("=========\n");
        System.out.println(name + "\n");  //default library name
        System.out.println("Publications");
        System.out.println("1) List");
        System.out.println("2) Add");
        System.out.println("3) Check out");
        System.out.println("4) Check in\n");
        System.out.println("Patron");
        System.out.println("5) List");
        System.out.println("6) Add\n");
        System.out.println("Files");
        System.out.println("7) Save library");
        System.out.println("0) Exit");

        Scanner sc = new Scanner(System.in);
        System.out.print("\n\nSelection(Choose between 1-7 from the options above or select 0 to exit): ");
        selection = sc.next();
        System.out.println("\n");
    }
    public static void listOfPublications()
    {
        System.out.println("\n=================");
        System.out.println("Library Catalogue");
        System.out.println("=================\n");
        System.out.println(name + '\n');
        System.out.println(L.toString());
    }

    public static void listOfPatrons()
    {
        System.out.println("\n===============");
        System.out.println("Library Patrons");
        System.out.println("===============\n");
        System.out.println(name + '\n');
        L.patronMenu();
    }
    public static void newPublication()
    {
        try
        {
            String title = console.readLine("Title: ");   if(title.isEmpty()) return;
            String author = console.readLine("Author: "); if(author.isEmpty()) return;
            int copyright = Integer.parseInt(console.readLine("Copyright: "));
            String runtime = console.readLine("Runtime (Press 'Enter' if not video): ");
            Publication p = null;
            if(runtime.isEmpty())
            {
                p = new Publication(title, author, copyright);
            }
            else
            {
                p = new Video(title, author, copyright, Integer.parseInt(runtime));
            }
            L.addPublication(p);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("Invalid year for copyright"))
            return;
        }
    }
    public static void newPatron()
    {
        if (L == null)
            L = new Library(name);
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
    public static void checkOutPublications()
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            System.out.println(L.toString());
            System.out.print("Which publication would you like to check out? ");
            int PublicationIndex = sc.nextInt();
            ArrayList l2 = L.getListOfPublication();
            Publication p = (Publication)l2.get(PublicationIndex);
            if (p.toString().contains("loaned to"))
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
            L.checkOut(PublicationIndex, PatronIndex);
            System.out.println(p.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            if (e.toString().contains("IndexOutOfBoundsException"))
                System.out.println ("You entered Index number that is out of bound.");
        }
    }
    public static void checkInPublications()
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

            System.out.println(L.toString());
            sc = new Scanner(System.in);
            System.out.print("Which publication would you like to return? ");
            int PublicationIndex = sc.nextInt();
            ArrayList l2 = L.getListOfPublication();
            Publication p = (Publication) l2.get(PublicationIndex);
            String LoanedTo = "";
            if (p.toString().contains("loaned to")) {
                if (p.toString().contains(pa.toString()))
                {
                    p.checkIn();
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
    public static void openLibrary() throws IOException
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the file name you are opening: ");
            String fileName = sc.nextLine();
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            L = new Library(br);
            System.out.println("Open filename: " + fileName);
        }
        catch (Exception e){
            if (e.toString().contains("FileNotFoundException"))
            {
                System.out.print('\n' + "You entered an invalid file name" + '\n');
                System.exit(0);
            }
        }
    }

    public static void saveLibrary() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is the filename you would like to save? ");
        String FileName = sc.next();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FileName), false));
        L.save(bw);
    }

    private static final String regexForCheckingValidEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    private static String selection;
    private static String title;
    private static String author;
    private static int copyright;
    private static int runtime;
    private static Library L = null;
    private static boolean didWeLoadDataFromFile;

    private static Console console = System.console();
    private static final String name = "Arlington Public Library";
}