import java.util.*;

public class Tasklist {
    private final ArrayList<Task> tasklist = new ArrayList<>();

    public void delete(int index) {
        System.out.println("Okay, I've digested this Food:\n" + this.getTask(index).toString2());
       this.tasklist.remove(index);
    }

    public void printTotal() {
        System.out.printf("Now you have %s Food in my tummy.\n", this.getSize());
    }

    public void printList() {
        System.out.println("Here are the Food in my tummy: ");
        for (int i = 1; i <= this.getSize(); i++) {
            System.out.println(i + ": " + this.tasklist.get(i - 1).toString2());
        }
    }

    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    public int getSize() {
        return this.tasklist.size();
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

}
