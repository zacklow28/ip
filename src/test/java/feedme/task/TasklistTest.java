package feedme.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import feedme.task.ToDo;

public class TasklistTest {
    Tasklist tasklist = new Tasklist();
    @Test
    public void addtaskandgetsize() {
        //case 1
        tasklist.addTask(new ToDo("todo borrow book"));
        assertEquals(1, tasklist.getSize());
        tasklist.addTask(new Deadline("deadline return book", "2007-12-03"));
        assertEquals(2, tasklist.getSize());
    }

    @Test
    public void getlist() {
        tasklist.addTask(new ToDo("todo borrow book"));
        tasklist.addTask(new Deadline("deadline return book", "2007-12-03"));
        assertEquals("[T][ ] todo borrow book", tasklist.getList().get(0).toString());
        assertEquals("[D][ ] deadline return book by: 2007-12-03", tasklist.getList().get(1).toString());
    }

    @Test
    public void gettask() {
        tasklist.addTask(new ToDo("todo borrow book"));
        tasklist.addTask(new Deadline("deadline return book", "2007-12-03"));
        assertEquals("[T][ ] todo borrow book", tasklist.getTask(0).toString());
        assertEquals("[D][ ] deadline return book by: Dec 03 2007", tasklist.getTask(1).toString2());
    }
        //case 1
    @Test
    public void delete() {
        //case 1
        tasklist.addTask(new ToDo("todo borrow book"));
        tasklist.addTask(new Deadline("deadline return book", "2007-12-03"));
        tasklist.delete(0);
        assertEquals(1, tasklist.getSize());
        assertEquals("[D][ ] deadline return book by: 2007-12-03", tasklist.getTask(0).toString());
    }

}