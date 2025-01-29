import java.util.*;
import java.io.*;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    //new format
    public String toString2() {
        return "[T]" + super.toString();
    }
}
