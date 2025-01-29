import java.time.format.DateTimeParseException;
import java.io.*;

public class FeedMe {
    private static Tasklist tasklist = new Tasklist();
    private static Storage storage = new Storage();

    public static void main(String[] args) throws IOException {
        String greeting =  "Hello! I'm FeedMe.\nWhat can I do for you?";
        String goodbye = "Munch. Hope to see you again soon!";
        String invalidCommand = "Invalid/Missing Command! Feed me again!";
        String invalidIndex = "Invalid/Missing Food Index! Feed me again!";
        String invalidName = "Invalid/Missing Food Name! Feed me again!";
        String invalidNameOrDate = "Invalid/Missing Food Name and/or Date! Feed me again!";
        String invalidDate = "Invalid/Missing Date! Feed me again!";

        System.out.println(greeting);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        storage.set(tasklist, br);
        String in = br.readLine();

        // loop
        while (in != null) {
            String[] inArr = in.split(" ", 2);  //split just on the first space
            if (in.equals("bye")) {
                System.out.println(goodbye);
                break;
            }
            else if (in.equals("list")) {
                tasklist.printList();
            }
            else if (inArr[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(inArr[1]) - 1;
                    Task curr = tasklist.getTask(index);
                    curr.mark();
                    System.out.println("Yay! I've marked this Food as eaten!\n" + curr.toString2());
                    storage.write(tasklist);
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
                    Task curr = tasklist.getTask(index);
                    curr.unmark();
                    System.out.println("Aww! I've unmarked this Food as not eaten!\n" + curr.toString2());
                    storage.write(tasklist);
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
                    tasklist.delete(index);
                    tasklist.printTotal();
                    storage.write(tasklist);
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
                    catch (DateTimeParseException e) {
                        System.out.println(invalidDate);
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
                    catch (DateTimeParseException e) {
                        System.out.println(invalidDate);
                        in = br.readLine();
                        continue;
                    }
                }
                else {
                    System.out.println(invalidCommand);
                    in = br.readLine();
                    continue;
                }
                tasklist.addTask(curr);
                System.out.println("Got it. I've added this Food:\n" + curr.toString2());
                storage.append(curr);
                tasklist.printTotal();
            }
            in = br.readLine();
        }
        br.close();
    }
}
