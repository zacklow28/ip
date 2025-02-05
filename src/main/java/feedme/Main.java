package feedme;

import java.io.IOException;

import feedme.task.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main class for FeedMe GUI
 */
public class Main extends Application {

    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/charmander.png"));
    private Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();

    /**
     * Sets up the GUI
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage; // Set the stage here so it can be accessed later

        // Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        initializeGui();

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sends start message when GUI is initialized
     */
    private void initializeGui() {
        dialogContainer.getChildren().add(DialogBox.getBotDialog(ui.greet(), botImage));
    }

    /**
     * Handles user input and updates the UI
     * @throws IOException if the file cannot be read
     */
    private void handleUserInput() throws IOException {
        // Handle the file input and update the UI.
        String fileName = userInput.getText();
        String fileResult = storage.setUsingString(taskList, fileName);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(fileName, userImage),
                DialogBox.getBotDialog(fileResult, botImage)
        );
        userInput.clear();
        if (fileResult.equals("Tummy set!") || fileResult.contains("Created a new stomach")) {
            handleCommand();
        }
    }

    /**
     * Handles commands after file is set and updates the UI
     */
    private void handleCommand() {
        userInput.setOnAction(event -> {
            try {
                // process commands
                String userText = userInput.getText();
                String dukeText = ui.handleCommand(userText, taskList, storage);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(userText, userImage),
                        DialogBox.getBotDialog(dukeText, botImage)
                );
                // If the user types "bye", wait for 1 second before closing the stage
                if (userText.equals("bye")) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1)); // 1-second delay
                    pause.setOnFinished(e -> stage.close()); // Close the stage after the delay
                    pause.play(); // Start the pause transition
                }
                userInput.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
