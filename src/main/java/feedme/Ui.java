package feedme;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import feedme.task.Deadline;
import feedme.task.Event;
import feedme.task.Task;
import feedme.task.TaskList;
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
     * Processes the user input to update the taskList
     * @param in user input to be passed to the parser
     * @param taskList taskList to be updated
     * @param storage storage for reading from and writing to file
     * @param br buffer reader for reading user input
     * @throws IOException if input cannot be read
     */
    public void respondToUserBasedOnCommand(String in, TaskList taskList, Storage storage, BufferedReader br)
            throws IOException {
        Task task;
        while (in != null) {
            String command = Parser.parseInputToCommand(in);
            String goodbye = "Munch. Hope to see you again soon!";
            String invalidCommand = "Invalid/Missing Command! Feed me again!";
            String invalidIndex = "Invalid/Missing Index! Feed me again!";
            String invalidNameOrFormat = "Invalid/Missing Parameter or Format! Feed me again!";
            switch (command) {
            case "goodbye":
                System.out.println(goodbye);
                return;
            case "list":
                taskList.printListOfTasks();
                break;
            case "mark":
                try {
                    int index = Parser.parseInputToIndex(in);
                    task = taskList.getTask(index);
                    task.mark();
                    System.out.println("Yay! I've marked this Food as eaten!\n" + task.toNewFormat());
                    storage.write(taskList);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "unmark":
                try {
                    int index = Parser.parseInputToIndex(in);
                    task = taskList.getTask(index);
                    task.unmark();
                    System.out.println("Okay! I've unmarked this Food as not eaten!\n" + task.toNewFormat());
                    storage.write(taskList);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "delete":
                try {
                    int index = Parser.parseInputToIndex(in);
                    taskList.deleteTask(index);
                    taskList.printTotalTasks();
                    storage.write(taskList);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(invalidIndex);
                }
                break;
            case "find":
                try {
                    String name = Parser.parseInputToName(in);
                    findTaskAndRespond(name, taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(invalidNameOrFormat);
                }
                break;
            case "task":
                try {
                    ArrayList<String> result = Parser.parseInputToArrayOfTaskParameters(in);
                    task = returnTaskObject(result);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(invalidNameOrFormat);
                    break;
                } catch (DateTimeParseException e) {
                    String invalidDate = "Invalid/Missing Date! Feed me again!";
                    System.out.println(invalidDate);
                    break;
                }
                taskList.addTask(task);
                System.out.println("Got it. I've added this Food:\n" + task.toNewFormat());
                storage.append(task);
                taskList.printTotalTasks();
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
    public Task returnTaskObject(ArrayList<String> result)
            throws ArrayIndexOutOfBoundsException, DateTimeParseException {
        String type = result.get(0);
        Task task;
        if (type.equals("todo")) {
            task = new ToDo(result.get(1));
        } else if (type.equals("deadline")) {
            task = new Deadline(result.get(1), result.get(2));
        } else { //event
            task = new Event(result.get(1), result.get(2), result.get(3));
        }
        return task;
    }

    /**
     * Searches for a tasks in the tasklist that matches input
     * @param in user input
     * @param taskList tasklist to be searched
     */
    public void findTaskAndRespond(String in, TaskList taskList) {
        System.out.println("Finding...");
        TaskList newTaskList = new TaskList();
        for (Task t : taskList.getListOfTasks()) {
            if (t.getName().contains(in)) {
                newTaskList.addTask(t);
            }
        }
        if (newTaskList.getSize() == 0) {
            System.out.println("No matches found in my tummy!");
        } else {
            newTaskList.printListOfTasks();
        }
    }
}
