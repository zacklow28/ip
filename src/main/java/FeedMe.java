import java.util.*;
import java.io.*;

public class FeedMe {

    public static void delete(ArrayList<Task> tasklist, int index) {
        System.out.println("Okay, I've removed this task:\n" + tasklist.get(index));
        tasklist.remove(index);
    }

    public static void printTotal(ArrayList<Task> tasklist) {
        String taskword = tasklist.size() == 1 ? "task" : "tasks";
        System.out.printf("Now you have %s %s in the list.\n", tasklist.size(), taskword);
    }

    public static void printList(ArrayList<Task> tasklist) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= tasklist.size(); i++) {
            System.out.println(i + ": " + tasklist.get(i - 1).toString());
        }
    }

    public static void addTask(ArrayList<Task> tasklist, Task task) {
        tasklist.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
    }

    public static void main(String[] args) throws IOException {
        String greeting =  "Hello! I'm FeedMe.\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String invalidCommand = "Invalid/Missing Command! Please try again.\n";
        String invalidIndex = "Invalid/Missing Task Number! Please try again.\n";
        String invalidName = "Invalid/Missing Task Name! Please try again.\n";
        String invalidNameOrDate = "Invalid/Missing Task Name and/or Date! Please try again.\n";

        System.out.println(greeting);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        ArrayList<Task> tasklist = new ArrayList<>();

        // loop
        while (in != null) {
            String[] inArr = in.split(" ", 2);  //split just on the first space
            if (in.equals("bye")) {
                System.out.println(goodbye);
                break;
            }
            else if (in.equals("list")) {
                printList(tasklist);
            }
            else if (inArr[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    Task curr = tasklist.get(index);
                    curr.mark();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(invalidIndex);
                    in = br.readLine();
                    continue;
                }
            }
            else if (inArr[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    Task curr = tasklist.get(index);
                    curr.unmark();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(invalidIndex);
                    in = br.readLine();
                    continue;
                }
            }
            else if (inArr[0].equals("delete")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    delete(tasklist, index);
                    printTotal(tasklist);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(invalidIndex);
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
                        System.out.println(invalidName);
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
                        System.out.println(invalidNameOrDate);
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
                        System.out.println(invalidNameOrDate);
                        in = br.readLine();
                        continue;
                    }
                }
                else {
                    System.out.println(invalidCommand);
                    in = br.readLine();
                    continue;
                }
                addTask(tasklist, curr);
                printTotal(tasklist);
            }
            in = br.readLine();
        }
        br.close();
    }
}
