import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
       assertEquals("[D][ ] deadline return book (by: Sunday)", new Deadline("deadline return book", "Sunday").toString());
   }

   @Test
   public void TestEvent() {
       //case 1
       assertEquals("[E][ ] event project meeting (from: Mon 2pm to: 4pm)", new Event("event project meeting", "Mon 2pm /to 4pm").toString());
   }
}
