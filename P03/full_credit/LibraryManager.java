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

        L.checkOut(1, "Rodney Nguyen");

        L.toStringLibrary();
    }
}