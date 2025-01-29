import java.util.*;
import java.io.*;

public class Parser {

    public static String parse(String in) {
        String[] inArr = in.split(" ", 2);  //split just on the first space
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

    public static int parseInt(String in) {
        String[] inArr = in.split(" ", 2);  //split just on the first space
        return Integer.parseInt(inArr[1]) - 1;
    }

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
    }    //split just on the first space
}
