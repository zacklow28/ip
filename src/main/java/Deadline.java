import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Deadline extends Task {
    private LocalDate duedate;

    public Deadline(String name, String duedate) {
        super(name);
        this.duedate = LocalDate.parse(duedate);
    }

    public Deadline(String name, boolean done, String duedate) {
        super(name, done);
        this.duedate = LocalDate.parse(duedate);
    }

    public String getDate() {
        return this.duedate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), this.duedate);
    }

    public String toString2() {
        return String.format("[D]%s by: %s", super.toString(), this.getDate());
    }
}
