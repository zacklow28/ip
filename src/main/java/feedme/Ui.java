package feedme;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import feedme.task.Deadline;
import feedme.task.Event;
import feedme.task.Task;
import feedme.task.Tasklist;
import feedme.task.ToDo;

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
            String invalidIndex = "Invalid/Missing Index! Feed me again!";
            String invalidNameOrFormat = "Invalid/Missing Parameter or Format! Feed me again!";
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
            case "find":
                try {
                    String name = Parser.parseName(in);
                    findTask(name, tasklist);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(invalidNameOrFormat);
                }
                break;
            case "task":
                try {
                    ArrayList<String> result = Parser.parseTask(in);
                    curr = processTask(result);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(invalidNameOrFormat);
                    break;
                } catch (DateTimeParseException e) {
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
        } else if (type.equals("deadline")) {
            curr = new Deadline(result.get(1), result.get(2));
        } else { //event
            curr = new Event(result.get(1), result.get(2), result.get(3));
        }
        return curr;
    }

    public void findTask(String in, Tasklist tasklist) {
        System.out.println("Finding...");
        Tasklist tasklist2 = new Tasklist();
        for (Task t : tasklist.getList()) {
            if (t.getName().contains(in)) {
                tasklist2.addTask(t);
            }
        }
        if (tasklist2.getSize() == 0) {
            System.out.println("No matches found in my tummy!");
        } else {
            tasklist2.printList();
        }
    }
}
