package mdi;

import library.Library;
import library.Publication;
import library.Patron;
import library.Video;
import library.InvalidRuntimeException;

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
        //L = new Library("Arlington Public Library");
        didWeLoadDataFromFile = false;

        boolean wantToExit = false;
        initialMenu();
        while (wantToExit == false)
        {
            if (selection.contains("0")) {
                wantToExit = true;
                Scanner sc = new Scanner(System.in);
                System.out.print("Do you want to save data before exiting? (type Y or N): ");
                String yesOrNo = sc.nextLine();
                if (yesOrNo.toUpperCase().contains("Y")) {
                    //updateDataToInputDataFile();
                    saveLibrary();
                }
            }
            else if (selection.contains("1")) {
                listOfPublications();
                initialMenu();
            } else if (selection.contains("2")) {
                newPublication();
                initialMenu();
            } else if (selection.contains("3")) {
                checkOutPublications();
                initialMenu();
            } else if (selection.contains("4")) {
                checkInPublications();
                initialMenu();
            } else if (selection.contains("5")) {
                listOfPatrons();
                initialMenu();
            } else if (selection.contains("6")) {
                newPatron();
                initialMenu();
            } else if (selection.contains("7")) {
                //loadData()
                openLibrary();
                initialMenu();
            } else if (selection.contains("8")) {
                //updateDataToInputDataFile();
                saveLibrary();
                initialMenu();
            }
            else
            {
                System.out.println("You entered invalid selection. Valid selection is a number between 1 and 7 OR 0 to exit.");
                initialMenu();
            }

        }
    }
    public static void initialMenu()
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
        System.out.println("7) Open library");
        System.out.println("8) Save library");
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
        if (L == null)
            L = new Library(name);

        System.out.println("B) Book");
        System.out.println("V) Video");
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\nChoose B to add new book OR choose V to add new Video: ");
        String choice = sc.next();
        System.out.println("\n");
        if (!(choice.toUpperCase().contains("B") || choice.toUpperCase().contains(("V")))) {
            System.out.println("You just entered invalid choice");
            System.out.print("\n\nChoose B to add new book OR choose V to add new Video: ");
            choice = sc.next();
            if (!(choice.toUpperCase().contains("B") || choice.toUpperCase().contains(("V")))) {
                System.out.println("You entered invalid choice twice. We can't add new Publication to our system.");
                return;
            }
        }
        if (choice.toUpperCase().contains("B")) {
            Publication p;
            try {
                System.out.print("Enter new Book's title: ");
                title = sc.next();
                System.out.print("Enter new Book's author: ");
                author = sc.next();
                System.out.print("Enter new Book's copyright: ");
                copyright = sc.nextInt();
                p = new Publication(title, author, copyright);
                L.addPublication(p);
                System.out.println("Added Book \"" + title + "\" by " + author + ", copyright " + copyright);
            } catch (Exception e) {
                System.out.println(e.toString());
                if (e.toString().contains("Invalid year for copyright")) {
                    try {
                        System.out.println("If you want to add new Book, please enter valid year for copyright (year should be between 1900 to present year).");
                        System.out.print("Enter new Publication's copyright: ");
                        copyright = sc.nextInt();
                        p = new Publication(title, author, copyright);
                        L.addPublication(p);
                        System.out.println("Added Book \"" + title + "\" by " + author + ", copyright " + copyright);
                    } catch (Exception e2) {
                        System.out.println(e.toString());
                        System.out.println("You entered invalid copyright for two times. The new book is NOT added to our system.");
                    }
                }
            }
        }
        else   //add Video
        {
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

            L.toString();
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

    /*
    public static void loadData() throws IOException
    {
        if (didWeLoadDataFromFile)
            return;
        if (L == null)
            L = new Library("Arlington Public Library");

        String inputFileName = "library.txt";
        FileReader fr = new FileReader(inputFileName);
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
                                L.toStringLibrary();
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
                                L.toStringLibrary();
                            }
                        }

                    }
                }
            }
            System.out.println("File: " + inputFileName + "\n\n");
            didWeLoadDataFromFile = true;
        }

        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Please check your InputData.txt file for valid data.");
        }
    }*/
    /*public static void openLibrary() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = null;
        String line;

        System.out.print("What is the filename you would like to open? ");

        try
        {
            br = new BufferedReader(new FileReader("//Users//rodneynguyen//Downloads//P06-RodneyDraft//" + sc.next()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "The File was not found");
            System.exit(0);
        }
        System.out.print("\n");
        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (Exception e2)
        {
            System.out.println(e2.getMessage() + "Error reading file");
        }
    } */

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
                System.out.print("You entered an invalid file name");
                return;
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