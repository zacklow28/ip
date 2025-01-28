import java.util.*;
import java.io.*;

public class Task {
    private String name;
    private Boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
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

    public String toString2() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + name;}
}
