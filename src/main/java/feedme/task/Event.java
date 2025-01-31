package feedme.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event inherits from Task. Have extra attributes startdate and enddate
 */
public class Event extends Task {
    private LocalDate startdate;
    private LocalDate enddate;

    /**
     * Constructor
     * @param name name of task
     * @param date date of event in format "MMM dd yyyy /to MMM dd yyyy"
     */
    public Event(String name, String date) {
        super(name);
        this.startdate = LocalDate.parse(date.split(" /to ")[0]);
        this.enddate = LocalDate.parse(date.split(" /to ")[1]);
    }

    /**
     * Constructor
     * @param name name of task
     * @param done if task is done
     * @param date date of event in format "MMM dd yyyy /to MMM dd yyyy"
     */
    public Event(String name, boolean done, String date) {
        super(name, done);
        this.startdate = LocalDate.parse(date.split(" /to ")[0]);
        this.enddate = LocalDate.parse(date.split(" /to ")[1]);
    }

    /**
     * Constructor
     * @param name name of task
     * @param startdate startdate of event in format "yyyy-MM-dd"
     * @param enddate enddate of event in format "yyyy-MM-dd"
     */
    public Event(String name, String startdate, String enddate) {
        super(name);
        this.startdate = LocalDate.parse(startdate);
        this.enddate = LocalDate.parse(enddate);
    }

    /**
     * Returns start date
     * @return String start date
     */
    public String getStartDate() {
        return this.startdate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns end date
     * @return String end date
     */
    public String getEndDate() {
        return this.enddate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     *  Returns string in format "[E]name from: YYYY-MM-DD to: YYYY-MM-DD"
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("[E]%s from: %s to: %s", super.toString(), this.startdate, this.enddate);
    }

    /**
     * Returns string in format "[E]name from: MMM dd yyyy to: MMM dd yyyy"
     * @return String representation
     */
    //new format
    public String toString2() {
        return String.format("[E]%s from: %s to: %s", super.toString(), this.getStartDate(), this.getEndDate());
    }
}
