package feedme.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline inherits from Task. Has a duedate
 */
public class Deadline extends Task {
    private LocalDate duedate;

    /**
     *  Constructor
     * @param name name of task
     * @param duedate date of task
     */
    public Deadline(String name, String duedate) {
        super(name);
        this.duedate = LocalDate.parse(duedate);
    }

    /**
     *  Constructor with done
     * @param name name of task
     * @param done if task is done
     * @param duedate date of task
     */
    public Deadline(String name, boolean done, String duedate) {
        super(name, done);
        this.duedate = LocalDate.parse(duedate);
    }

    /**
     *  Returns the date of the task in the format of "MMM dd yyyy"
     *  @return String date
     */
    public String getDate() {
        return this.duedate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     *  Returns string in format "[E]name by: YYYY-MM-DD"
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), this.duedate);
    }

    /**
     *  Returns string in format "[E]name by: MMM dd yyyy"
     * @return String representation
     */
    //new format
    public String toString2() {
        return String.format("[D]%s by: %s", super.toString(), this.getDate());
    }
}
