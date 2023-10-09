package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

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

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + "\n");
        bw.write(email + "\n");
    }

    public void Patron(BufferedReader br) throws IOException
    {

    }

    @Override
    public String toString() {
        return name + "(" + email + ")";
    }
}
