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

/**
 * Ui class that deals with interactions with the user
 */
public class Ui {

    /**
     * Prints a greeting message to the user
     */
    public void greet() {
        String greeting = "Hello! I'm FeedMe.\nWhat can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Processes the user input to update the tasklist
     * @param in user input to be passed to the parser
     * @param tasklist tasklist to be updated
     * @param storage storage for reading from and writing to file
     * @param br buffer reader for reading user input
     * @throws IOException if input cannot be read
     */
    public void process(String in, Tasklist tasklist, Storage storage, BufferedReader br) throws IOException {
        Task curr;
        while (in != null) {
            String command = Parser.parse(in);
            String goodbye = "Munch. Hope to see you again soon!";
            String invalidCommand = "Invalid/Missing Command! Feed me again!";
            String invalidIndex = "Invalid/Missing Index! Feed me again!";
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
                } catch (ArrayIndexOutOfBoundsException e) {
                    String invalidNameOrFormat = "Invalid/Missing Parameter or Format! Feed me again!";
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

    /**
     * Processes the user input that includes a type of task into a task object
     * @param result user input
     * @return Task task to be added into the tasklist
     * @throws ArrayIndexOutOfBoundsException if the input is invalid
     * @throws DateTimeParseException if the date is invalid
     */
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
}
