import java.time.format.DateTimeParseException;
import java.io.*;

public class FeedMe {
    private static Tasklist tasklist = new Tasklist();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();

    public static void main(String[] args) throws IOException {
        ui.greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        storage.set(tasklist, br);
        String in = br.readLine();
        ui.process(in, tasklist, storage, br);
        br.close();
    }
}
