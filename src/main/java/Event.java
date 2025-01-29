import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Event extends Task {
    private LocalDate startdate;
    private LocalDate enddate;

    public Event(String name, String date) {
        super(name);
        this.startdate = LocalDate.parse(date.split(" /to ")[0]);
        this.enddate = LocalDate.parse(date.split(" /to ")[1]);
    }

    public Event(String name, boolean done, String date) {
        super(name, done);
        this.startdate = LocalDate.parse(date.split(" /to ")[0]);
        this.enddate = LocalDate.parse(date.split(" /to ")[1]);
    }

    public Event(String name, String startdate, String enddate) {
        super(name);
        this.startdate = LocalDate.parse(startdate);
        this.enddate = LocalDate.parse(enddate);
    }

    public String getStartDate() {
        return this.startdate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String getEndDate() {
        return this.enddate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s from: %s to: %s", super.toString(), this.startdate, this.enddate);
    }

    //new format
    public String toString2() {
        return String.format("[E]%s from: %s to: %s", super.toString(), this.getStartDate(), this.getEndDate());
    }
}
