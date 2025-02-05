package feedme.task;

import java.util.ArrayList;

/**
 * Class which is a collection of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Deletes a task from the Collection
     * @param index The index of the task, start from 0
     */
    public String deleteTask(int index) {
        String output = "Okay, I've digested this Food:\n" + this.getTask(index).toNewFormat();
        this.tasks.remove(index);
        return output;
    }

    /**
     * Prints the total number of tasks
     */
    public void printTotalTasks() {
        System.out.printf("Now you have %s Food in my tummy.\n", this.getSize());
    }

    /**
     * Prints the total number of tasks
     */
    public String getTotalTasks() {
        return String.format("Now you have %s Food in my tummy.\n", this.getSize());
    }

    /**
     * Prints a string of the list of tasks
     */
    public void printListOfTasks() {
        System.out.println("Here are the Food in my tummy: ");
        for (int i = 1; i <= this.getSize(); i++) {
            System.out.println(i + ": " + this.tasks.get(i - 1).toNewFormat());
        }
    }

    @Override
    public String toString() {
        String output = "Here are the Food in my tummy: ";
        for (int i = 1; i <= this.getSize(); i++) {
            output = output.concat("\n" + i + ": " + this.tasks.get(i - 1).toNewFormat());
        }
        return output;
    }

    /**
     * Returns the list
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getListOfTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index
     * @param index The index of the task in the array list
     * @return Task
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the size of the list
     * @return int of the total number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

}
