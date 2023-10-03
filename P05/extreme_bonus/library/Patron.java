package library;
/** A person who has the authority to check out the library's resources. */
public class Patron
{
    private String name;
    private String email;

    public Patron(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String toStringPatron()
    {
        return name + "(" + email + ")";
    }
}
