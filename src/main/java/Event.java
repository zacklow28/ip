import java.util.*;
import java.io.*;

public class Event extends Task {
    private String startdate;
    private String enddate;

    public Event(String name, String date) {
        super(name);
        this.startdate = date.split(" /to ")[0];
        this.enddate = date.split(" /to ")[1];
    }

    public Event(String name, boolean done, String date) {
        super(name, done);
        this.startdate = date.split(" /to ")[0];
        this.enddate = date.split(" /to ")[1];
    }

    public String getStartDate() {
        return this.startdate;
    }

    public String getEndDate() {
        return this.enddate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s from: %s to: %s", super.toString(), this.startdate, this.enddate);
    }
}
