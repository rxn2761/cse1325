package library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.*;
/** A library video that can be checked out by patrons. */
public class Video extends Publication {
    private Duration runtime = null;

    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);
        if (runtime < 0)
            throw new InvalidRuntimeException(title, runtime);
        this.runtime = Duration.ofMinutes(runtime);
    }

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        String ObjectInfo = toStringBuilderForSave("Video", "," + runtime.toMinutes());
        String[] temp = ObjectInfo.split(",");
        if (temp.length == 5)
        {
            bw.write("VideoCheckedIn" + "\n");
            bw.write(temp[1] + "\n");
            bw.write(temp[2] + "\n");
            bw.write(temp[3] + "\n");
            bw.write(temp[4] + "\n");
        }
        else
        {
            bw.write("VideoCheckedOut" + "\n");
            bw.write(temp[1] + "\n");
            bw.write(temp[2] + "\n");
            bw.write(temp[3] + "\n");
            bw.write(temp[4] + "\n");
            bw.write(temp[5] + "\n");
            bw.write(temp[6] + "\n");
        }
    }

    public void Video(BufferedReader br) throws IOException
    {

    }

    @Override
    public String toString() {
        return toStringBuilder("Video", ", runtime " + runtime.toMinutes() + " minutes");
    }
}
