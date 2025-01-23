import java.util.*;
import java.io.*;

public class FeedMe {

    public static void main(String[] args) throws IOException {
        String greeting =  "Hello! I'm FeedMe.\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        System.out.println(greeting);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        ArrayList<Task> tasklist = new ArrayList<>();

        // loop
        while (in != null) {
            String[] inArr = in.split(" ", 2);  //split just on the first space
            if (inArr[0].equals("bye")) {
                System.out.println(goodbye);
                break;
            }
            else if (inArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= tasklist.size(); i++) {
                    System.out.println(i + ": " + tasklist.get(i - 1).toString());
                }
            }
            else if (inArr[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    Task curr = tasklist.get(index);
                    curr.mark();
                    System.out.println("Nice! I've marked this task as done!\n" + curr);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid/Missing Task Number! Please try again.");
                    in = br.readLine();
                    continue;
                }
            }
            else if (inArr[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    Task curr = tasklist.get(index);
                    curr.unmark();
                    System.out.println("Okay! I've marked this task as not done!\n" + curr);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid/Missing Task Number! Please try again.");
                    in = br.readLine();
                    continue;
                }
            }
            else {
                Task curr;
                if (inArr[0].equals("todo")) {
                    try {
                        curr = new ToDo(inArr[1]);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Missing Task Name! Please try again.");
                        in = br.readLine();
                        continue;
                    }
                }
                else if (inArr[0].equals("deadline")) {
                    try {
                        String[] inArr2 = inArr[1].split(" /by "); //split second part to get due date
                        curr = new Deadline(inArr2[0], inArr2[1]);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Missing/Invalid Task Name and/or Date! Please try again.");
                        in = br.readLine();
                        continue;
                    }
                }
                else if (inArr[0].equals("event")) {
                    try {
                        String[] inArr2 = inArr[1].split(" /from "); //split second part to get due date
                        curr = new Event(inArr2[0], inArr2[1]);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Missing/Invalid Task Name and/or Date! Please try again.");
                        in = br.readLine();
                        continue;
                    }
                }
                else {
                    System.out.println("Invalid Command! Please try again.");
                    in = br.readLine();
                    continue;
                }
                tasklist.add(curr);
                System.out.println("Got it. I've added this task:\n" + curr);
                String taskword = tasklist.size() == 1 ? "task" : "tasks";
                System.out.printf("Now you have %s %s in the list.\n", tasklist.size(), taskword);
            }
            in = br.readLine();
        }
        br.close();
    }
}
