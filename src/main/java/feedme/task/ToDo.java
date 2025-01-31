package feedme.task;

/**
 * ToDo inherits from Task. No extra attributes
 */
public class ToDo extends Task {

    /**
     * Constructor
     * @param name The name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name name of task
     * @param done if task is done
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Returns string in format "[T]name"
     * @return String representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string in format "[T]name"
     * @return String representation
     */
    //new format
    public String toString2() {
        return "[T]" + super.toString();
    }
}
