package feedme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import feedme.task.Event;
import feedme.task.Task;
import feedme.task.Tasklist;
import feedme.task.ToDo;

public class Storage {
    private String filepath;

    public void initialize(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Created a new stomach at: " + filepath);
        }
        this.filepath = filepath;
    }

    public void retrieveFrom(Tasklist tasklist, String filepath) throws IOException {
        File file = new File(filepath);
        this.filepath = filepath;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replace("to:", "/to");
            char taskType = line.charAt(1);
            String food = line.substring(7).trim(); //exclude the [ ][ ]
            switch (taskType) {
            case 'T':
                if (line.contains("[X]")) {
                    tasklist.addTask(new ToDo(food, true));
                } else {
                    tasklist.addTask(new ToDo(food));
                }
                break;
            case 'D':
                String[] foodArr = food.split(" by: ");
                if (line.contains("[X]")) {
                    tasklist.addTask(new Deadline(foodArr[0], true, foodArr[1]));
                } else {
                    tasklist.addTask(new Deadline(foodArr[0], foodArr[1]));
                }
                break;
            case 'E':
                String[] foodArr2 = food.split(" from: ");
                if (line.contains("[X]")) {
                    tasklist.addTask(new Event(foodArr2[0], true, foodArr2[1]));
                } else {
                    tasklist.addTask(new Event(foodArr2[0], foodArr2[1]));
                }
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        }
        scanner.close();
    }

    public void set(Tasklist tasklist, BufferedReader br) throws IOException {
        System.out.println("Tummy path (with extension) : ");
        String filepath = br.readLine();
        //read from file
        while (filepath != null) {
            try {
                this.retrieveFrom(tasklist, filepath);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Tummy location not found.");
                this.initialize(filepath);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid/Missing content! Feed me again!");
                filepath = br.readLine();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid/Missing Date! Feed me again!");
                filepath = br.readLine();
            }

        }
        System.out.println("Tummy set!");
    }

    public void append(Task curr) throws IOException {
        FileWriter fw = new FileWriter(this.filepath, true);
        fw.write(curr.toString() + "\n");
        fw.close();
    }

    public void write(Tasklist tasklist) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (Task t : tasklist.getList()) {
            fw.write(t.toString() + "\n");
        }
        fw.close();
    }
}
