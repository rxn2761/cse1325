public class Patron
{
    private String name;
    private String email;

    Patron(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public String toStringPatron()
    {
        return name + "(" + email + ")";
    }
}
