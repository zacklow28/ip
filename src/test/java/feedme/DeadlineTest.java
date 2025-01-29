package feedme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import feedme.task.ToDo;

public class DeadlineTest {
    @Test
    public void todo() {
        //case 1
        assertEquals("[D][ ] deadline return book by: 2007-12-03",
                new Deadline("deadline return book", "2007-12-03").toString());
        assertEquals("[D][ ] deadline return book by: Dec 03 2007",
                new Deadline("deadline return book", "2007-12-03").toString2());
    }
}
