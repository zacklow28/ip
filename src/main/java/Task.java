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
        System.out.println("Nice! I've marked this task as done!\n" + this);
    }

    public void unmark() {
        this.done = false;
        System.out.println("Okay! I've marked this task as not done!\n" + this);
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
