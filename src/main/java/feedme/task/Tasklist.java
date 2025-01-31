package feedme.task;

import java.util.ArrayList;

/**
 * Class which is a collection of tasks
 */
public class Tasklist {
    private final ArrayList<Task> tasklist = new ArrayList<>();

    /**
     * Deletes a task from the Collection
     * @param index The index of the task, start from 0
     */
    public void delete(int index) {
        System.out.println("Okay, I've digested this Food:\n" + this.getTask(index).toString2());
        this.tasklist.remove(index);
    }

    /**
     * Prints the total number of tasks
     */
    public void printTotal() {
        System.out.printf("Now you have %s Food in my tummy.\n", this.getSize());
    }

    /**
     * Prints a string of the list of tasks
     */
    public void printList() {
        System.out.println("Here are the Food in my tummy: ");
        for (int i = 1; i <= this.getSize(); i++) {
            System.out.println(i + ": " + this.tasklist.get(i - 1).toString2());
        }
    }

    /**
     * Returns the list
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    /**
     * Returns the task at the specified index
     * @param index The index of the task in the array list
     * @return Task
     */
    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Returns the size of the list
     * @return int of the total number of tasks
     */
    public int getSize() {
        return this.tasklist.size();
    }

    /**
     * Adds a task to the list
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasklist.add(task);
    }

}
