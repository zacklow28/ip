package feedme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import feedme.task.Tasklist;

public class FeedMe {
    private static Tasklist tasklist = new Tasklist();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();

    public static void main(String[] args) throws IOException {
        ui.greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        storage.set(tasklist, br);
        String in = br.readLine();
        ui.process(in, tasklist, storage, br);
        br.close();
    }
}
