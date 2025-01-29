import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingTest {
   @Test
   public void TestToDo() {
       //case 1
       assertEquals("[T][ ] todo borrow book", new ToDo("todo borrow book").toString());
   }
   @Test
   public void TestDeadline() {
       //case 1
       assertEquals("[D][ ] deadline return book by: 2007-12-03",
               new Deadline("deadline return book", "2007-12-03").toString());
       assertEquals("[D][ ] deadline return book by: Dec 03 2007",
               new Deadline("deadline return book", "2007-12-03").toString2());
   }

   @Test
   public void TestEvent() {
       //case 1
       assertEquals("[E][ ] event project meeting from: 2001-02-03 to: 2001-02-04",
               new Event("event project meeting", "2001-02-03 /to 2001-02-04").toString());
       assertEquals("[E][ ] event project meeting from: Feb 03 2001 to: Feb 04 2001",
               new Event("event project meeting", "2001-02-03 /to 2001-02-04").toString2());
   }
}
