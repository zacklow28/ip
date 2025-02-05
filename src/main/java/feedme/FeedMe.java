package feedme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import feedme.task.TaskList;

/**
 * Main class for FeedMe
 */
public class FeedMe {
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();

    public static void main(String[] args) throws IOException {
        ui.greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        storage.setUsingBufferedReader(taskList, br);
        ui.loopAndRespond(br, taskList, storage);
        br.close();
    }
}
