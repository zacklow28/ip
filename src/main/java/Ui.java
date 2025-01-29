import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;

public class Ui {

    public void greet() {
        String greeting = "Hello! I'm FeedMe.\nWhat can I do for you?";
        System.out.println(greeting);
    }

    public void process(String in, Tasklist tasklist, Storage storage, BufferedReader br) throws IOException {
        Task curr;
        while (in != null) {
            String command = Parser.parse(in);
            String goodbye = "Munch. Hope to see you again soon!";
            String invalidCommand = "Invalid/Missing Command! Feed me again!";
            String invalidIndex = "Invalid/Missing Food Index! Feed me again!";
            switch (command) {
            case "goodbye":
                System.out.println(goodbye);
                return;
            case "printlist":
                tasklist.printList();
                break;
            case "mark":
                try {
                    int index = Parser.parseInt(in);
                    curr = tasklist.getTask(index);
                    curr.mark();
                    System.out.println("Yay! I've marked this Food as eaten!\n" + curr.toString2());
                    storage.write(tasklist);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "unmark":
                try {
                    int index = Parser.parseInt(in);
                    curr = tasklist.getTask(index);
                    curr.unmark();
                    System.out.println("Aww! I've unmarked this Food as not eaten!\n" + curr.toString2());
                    storage.write(tasklist);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "delete":
                try {
                    int index = Parser.parseInt(in);
                    tasklist.delete(index);
                    tasklist.printTotal();
                    storage.write(tasklist);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "task":
                try {
                    ArrayList<String> result = Parser.parseTask(in);
                    curr = processTask(result);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    String invalidNameOrFormat = "Invalid/Missing Food Name or Format! Feed me again!";
                    System.out.println(invalidNameOrFormat);
                    break;
                }
                catch (DateTimeParseException e) {
                    String invalidDate = "Invalid/Missing Date! Feed me again!";
                    System.out.println(invalidDate);
                    break;
                }
                tasklist.addTask(curr);
                System.out.println("Got it. I've added this Food:\n" + curr.toString2());
                storage.append(curr);
                tasklist.printTotal();
                break;
            default:
                System.out.println(invalidCommand);
            }
            in = br.readLine();
        }
    }

    public Task processTask(ArrayList<String> result) throws ArrayIndexOutOfBoundsException, DateTimeParseException {
        String type = result.get(0);
        Task curr;
        if (type.equals("todo")) {
            curr = new ToDo(result.get(1));
        }
        else if (type.equals("deadline")) {
            curr = new Deadline(result.get(1), result.get(2));
        }
        //event
        else {
            curr = new Event(result.get(1), result.get(2), result.get(3));
        }
        return curr;
    }
}
