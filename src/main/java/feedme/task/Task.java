package feedme.task;

/**
 * Class for tasks
 */
public class Task {
    private String name;
    private Boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor
     * @param name name of task
     * @param done if task is done
     */
    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the task. Sets done to true
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmarks the task. Sets done to false
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns name of task
     * @return String name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns task in format "[ ] name"
     * @return String representation
     */
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + name;
    }

    /**
     * Returns task in format "[ ] name"
     * @return String representation
     */
    //new format
    public String toString2() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + name;
    }
}
