import java.util.*;
import java.io.*;

public class FeedMe {
    private static final ArrayList<Task> tasklist = new ArrayList<>();

    public static void delete(ArrayList<Task> tasklist, int index) {
        System.out.println("Okay, I've swallowed this Food:\n" + tasklist.get(index));
        tasklist.remove(index);
    }

    public static void printTotal(ArrayList<Task> tasklist) {
        System.out.printf("Now you have %s Food in my tummy.\n", tasklist.size());
    }

    public static void printList(ArrayList<Task> tasklist) {
        System.out.println("Here are the Food in my tummy: ");
        for (int i = 1; i <= tasklist.size(); i++) {
            System.out.println(i + ": " + tasklist.get(i - 1).toString());
        }
    }

    public static void addTask(ArrayList<Task> tasklist, Task task) {
        tasklist.add(task);
    }

    public static void initializeFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Created a new stomach at: " + filepath);
        }
    }

    public static void retrieveFromFile(String filepath) throws IOException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("to:", "/to");
            char taskType = line.charAt(1);
            String food = line.substring(7).trim(); //exclude the [ ][ ]
            switch (taskType) {
            case 'T':
                if (line.contains("[X]")) {
                    addTask(tasklist, new ToDo(food, true));
                } else {
                    addTask(tasklist, new ToDo(food));
                }
                break;
            case 'D':
                String[] foodArr = food.split(" by: ");
                if (line.contains("[X]")) {
                    addTask(tasklist, new Deadline(foodArr[0], true, foodArr[1]));
                } else {
                    addTask(tasklist, new Deadline(foodArr[0], foodArr[1]));
                }
                break;
            case 'E':
                String[] foodArr2 = food.split(" from: ");
                if (line.contains("[X]")) {
                    addTask(tasklist, new Event(foodArr2[0], true, foodArr2[1]));
                } else {
                    addTask(tasklist, new Event(foodArr2[0], foodArr2[1]));
                }
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        }
        scanner.close();
    }

    public static void appendToFile(String filepath, Task curr) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(curr.toString() + "\n");
        fw.close();
    }

    public static void writeToFile(String filepath, ArrayList<Task> tasklist) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task t : tasklist) {
            fw.write(t.toString() + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        String greeting =  "Hello! I'm FeedMe.\nWhat can I do for you?\n";
        String goodbye = "Munch. Hope to see you again soon!\n";
        String invalidCommand = "Invalid/Missing Command! Feed me again!\n";
        String invalidIndex = "Invalid/Missing Food Index! Feed me again!\n";
        String invalidName = "Invalid/Missing Food Name! Feed me again!\n";
        String invalidNameOrDate = "Invalid/Missing Food Name and/or Date! Feed me again!\n";

        System.out.println(greeting);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Tummy path (with extension) : ");
        String filepath = br.readLine();

        //read from file
        while (filepath != null) {
            try {
                retrieveFromFile(filepath);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Tummy location not found.");
                initializeFile(filepath);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Food content corrupted! Feed me again!");
                filepath = br.readLine();
            }
        }
        System.out.println("Tummy set!");
        String in = br.readLine();

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
                    System.out.println("Yay! I've marked this Food as eaten!\n" + curr);
                    writeToFile(filepath, tasklist);
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
                    System.out.println("Aww! I've marked this Food as not eaten!\n" + curr);
                    writeToFile(filepath, tasklist);
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
                    writeToFile(filepath, tasklist);
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
                System.out.println("Got it. I've added this Food:\n" + curr);
                appendToFile(filepath, curr);
                printTotal(tasklist);
            }
            in = br.readLine();
        }
        br.close();
    }
}
