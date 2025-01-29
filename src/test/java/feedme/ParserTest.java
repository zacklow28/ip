package feedme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import feedme.task.ToDo;

public class ParserTest {
    @Test
    public void parse() {
        //case 1
        assertEquals("goodbye", Parser.parse("bye"));
        assertEquals("printlist", Parser.parse("list"));
        assertEquals("mark", Parser.parse("mark 1"));
        assertEquals("unmark", Parser.parse("unmark 1"));
        assertEquals("delete", Parser.parse("delete 1"));
        assertEquals("task", Parser.parse("todo borrow book"));
        assertEquals("task", Parser.parse("deadline return book /by 2007-12-03"));
        assertEquals("task", Parser.parse("event sell book /from 2007-12-03 /to 2007-12-04"));
        assertEquals("invalid", Parser.parse("timeline return book /by 2007-12-03"));
    }

    @Test
    public void parseInt() {
        //case 1
        assertEquals(0, Parser.parseInt("mark 1"));
        assertEquals(1, Parser.parseInt("delete 2"));
        assertEquals(32, Parser.parseInt("unmark 33"));
    }

    @Test
    public void parseTask() {
        //case 1
        assertEquals("[todo, borrow book]", Parser.parseTask("todo borrow book").toString());
        assertEquals("[deadline, return book, 2007-12-03]",
                Parser.parseTask("deadline return book /by 2007-12-03").toString());
        assertEquals("[event, sell book, 2007-12-03, 2007-12-04]",
                Parser.parseTask("event sell book /from 2007-12-03 /to 2007-12-04").toString());
    }
}
