import java.util.*;
public class LibraryManager
{
    public static void main(String[] args)
    {
        Publication P1 = new Publication("Fahrenheit 451", "Ray Bradbury", 2010);
        Publication P2 = new Publication("To Kill a Mockingbird", "Harper Lee", 2016);
        Publication P3 = new Publication("The Catcher in the Rye", "J.D. Salinger", 2013);

        Library L = new Library("Arlington Public Library");
        L.addPublication(P1);
        L.addPublication(P2);
        L.addPublication(P3);
        L.toStringLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Which book would you like to check out? ");
        int BookIndex = sc.nextInt();

        Scanner sc2 = new Scanner(System.in);
        System.out.print("Who are you? ");
        String personName = sc2.nextLine();

        L.checkOut(BookIndex, personName);

        L.toStringLibrary();

    }
}