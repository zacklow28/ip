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
    private static final String GOODBYE = "Munch. Hope to see you again soon!";
    private static final String INVALID_COMMAND = "Invalid/Missing Command! Feed me again!";
    private static final String INVALID_INDEX = "Invalid/Missing Index! Feed me again!";
    private static final String INVALID_NAME_OR_FORMAT = "Invalid/Missing Parameter or Format! Feed me again!";
    private static final String INVALID_INPUT = "Invalid/Missing Input! Feed me again!";
    private static final String INVALID_DATE = "Invalid/Missing Date! Feed me again!";
    /**
     * Prints a greeting message to the user
     */
    public String greet() {
        String greeting = "Hello! I'm FeedMe.\nWhat can I do for you?";
        String output = "Tummy path (with extension) : ";
        return greeting + "\n" + output;
    }

    /**
     * Handle the command logic for each individual input and respond to the user
     * @param in user input
     * @param taskList taskList to be modified
     * @param storage storage to be modified
     * @throws IOException if the file cannot be written to
     */
    public String handleCommand(String in, TaskList taskList, Storage storage) throws IOException {
        String command = Parser.parseInputToCommand(in);

        switch (command) {
        case "goodbye":
            return GOODBYE;
        case "list":
            return taskList.toString();
        case "mark":
            return handleMarkCommand(in, taskList, storage);
        case "unmark":
            return handleUnmarkCommand(in, taskList, storage);
        case "delete":
            return handleDeleteCommand(in, taskList, storage);
        case "find":
            return handleFindCommand(in, taskList);
        case "task":
            return handleTaskCommand(in, taskList, storage);
        default:
            return INVALID_COMMAND;
        }
    }

    private String handleTaskCommand(String in, TaskList taskList, Storage storage) throws IOException {
        Task task;
        try {
            ArrayList<String> result = Parser.parseInputToArrayOfTaskParameters(in);
            task = returnTaskObject(result);
        } catch (ArrayIndexOutOfBoundsException e) {
            return INVALID_NAME_OR_FORMAT;
        } catch (DateTimeParseException e) {
            return INVALID_DATE;
        }
        taskList.addTask(task);
        storage.append(task);
        return "Got it. I've added this Food:\n" + task.toNewFormat();
    }

    private String handleFindCommand(String in, TaskList taskList) {
        try {
            String[] names = in.split(" ");
            return findTaskAndRespond(taskList, names);
        } catch (ArrayIndexOutOfBoundsException e) {
            return INVALID_NAME_OR_FORMAT;
        }
    }

    private static String handleDeleteCommand(String in, TaskList taskList, Storage storage) {
        try {
            int index = Parser.parseInputToIndex(in);
            String output = taskList.deleteTask(index);
            storage.write(taskList);
            return output + "\n" + taskList.getTotalTasks();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return INVALID_INDEX;
        } catch (IOException e) {
            return INVALID_INPUT;
        }
    }

    private static String handleUnmarkCommand(String in, TaskList taskList, Storage storage) {
        Task task;
        try {
            int index = Parser.parseInputToIndex(in);
            task = taskList.getTask(index);
            task.unmark();
            String output = "Okay! I've unmarked this Food as not eaten!\n" + task.toNewFormat();
            storage.write(taskList);
            return output;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return INVALID_INDEX;
        } catch (IOException e) {
            return INVALID_INPUT;
        }
    }

    private static String handleMarkCommand(String in, TaskList taskList, Storage storage) {
        Task task;
        try {
            int index = Parser.parseInputToIndex(in);
            task = taskList.getTask(index);
            task.mark();
            String output = "Yay! I've marked this Food as eaten!\n" + task.toNewFormat();
            storage.write(taskList);
            return output;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return INVALID_INDEX;
        } catch (IOException e) {
            return INVALID_INPUT;
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
     * @param taskList tasklist to be searched
     * @param in user inputs
     */
    public String findTaskAndRespond(TaskList taskList, String... in) {
        TaskList newTaskList = new TaskList();
        for (Task t : taskList.getListOfTasks()) {
            for (int i = 1; i < in.length; i++) { //start from 1 to skip the "find" command
                if (t.getName().contains(in[i])) {
                    newTaskList.addTask(t);
                }
            }
        }
        if (newTaskList.getSize() == 0) {
            return "No matches found in my tummy!";
        } else {
            return newTaskList.toString();
        }
    }
}
