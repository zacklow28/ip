import java.util.*;
import java.io.*;

public class Task {
    private String name;
    private Boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + name;
    }
}
