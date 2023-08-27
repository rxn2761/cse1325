import java.net.InetAddress;

public class Hello
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress address;
            address = InetAddress.getLocalHost();
            String hostname = address.getHostName();

            System.out.println("Hello, " + hostname);
        }
        catch(Exception ex)
        {

        }
    }
}