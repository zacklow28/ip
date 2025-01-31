package feedme;

import java.util.ArrayList;

/**
 * Parser class that deals with making sense of the user command
 */
public class Parser {

    /**
     * Parses user input into a command
     * @param in user input
     * @return String command
     */
    public static String parse(String in) {
        String[] inArr = in.split(" ", 2); //split just on the first space
        if (in.equals("bye")) {
            return "goodbye";
        } else if (in.equals("list")) {
            return "printlist";
        } else if (inArr[0].equals("mark")) {
            return "mark";
        } else if (inArr[0].equals("unmark")) {
            return "unmark";
        } else if (inArr[0].equals("delete")) {
            return "delete";
        } else if (inArr[0].equals("todo") || inArr[0].equals("deadline") || inArr[0].equals("event")) {
            return "task";
        } else {
            return "invalid";
        }
    }

    /**
     * Parses user input that includes the index into index of the list
     * @param in user input
     * @return int index
     */
    public static int parseInt(String in) {
        String[] inArr = in.split(" ", 2); //split just on the first space
        return Integer.parseInt(inArr[1]) - 1;
    }

    /**
     * Parses user input that includes a type of task into an array list
     * @param in user input
     * @return ArrayList of strings, each string is a part of the task. [0] is type of task, [1] is the name
     */
    public static ArrayList<String> parseTask(String in) {
        String[] inArr = in.split(" ", 2);
        ArrayList<String> outArr = new ArrayList<String>();
        if (inArr[0].equals("todo")) {
            outArr.add(inArr[0]);
            outArr.add(inArr[1]);
        } else if (inArr[0].equals("deadline")) {
            outArr.add(inArr[0]);
            outArr.add(inArr[1].split(" /by ")[0]);
            outArr.add(inArr[1].split(" /by ")[1]);
        } else if (inArr[0].equals("event")) {
            outArr.add(inArr[0]);
            outArr.add(inArr[1].split(" /from ")[0]);
            String input = inArr[1].split(" /from ")[1];
            outArr.add(input.split(" /to ")[0]);
            outArr.add(input.split(" /to ")[1]);
        }
        return outArr;
    }
}
