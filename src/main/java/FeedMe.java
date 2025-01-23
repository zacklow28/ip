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
        ArrayList<String> list = new ArrayList<String>();

        // loop and add content to list until user types "bye"
        while (true) {
            if (in.equals("bye")) {
                break;
            }
            if (in.equals("list")) {
                int[] iteration = {1};
                //use iteration[0], then increment it for each element
                list.forEach(x -> System.out.println((iteration[0]++) + ": " + x));
            }
            else {
                list.add(in);
                System.out.println("added: " + in);
            }
            in = br.readLine();
        }
        System.out.println(goodbye);
    }
}
