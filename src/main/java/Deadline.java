import java.util.*;
import java.io.*;

public class Deadline extends Task {
    private String duedate;

    public Deadline(String name, String duedate) {
        super(name);
        this.duedate = duedate;
    }

    public String getDate() {
        return this.duedate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.duedate);
    }

}
