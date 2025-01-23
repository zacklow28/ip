import java.util.*;
import java.io.*;

public class FeedMe {
    public static void main(String[] args) throws IOException {
        String greeting =  "Hello! I'm FeedMe.\n" +
                "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        System.out.println(greeting);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();

        // loop and echo content until user types "bye"
        while (!in.equals("bye")) {
            System.out.println(in);
            in = br.readLine();
        }
        System.out.println(goodbye);

    }
}
