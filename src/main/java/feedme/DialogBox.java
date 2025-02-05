package feedme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * class for a dialogue box in the UI
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     *  Constructor
     * @param string input text
     * @param image user image
     */
    public DialogBox(String string, Image image) {
        text = new Label(string);
        displayPicture = new ImageView(image);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String string, Image image) {
        return new DialogBox(string, image);
    }

    public static DialogBox getBotDialog(String string, Image image) {
        var db = new DialogBox(string, image);
        db.flip();
        return db;
    }
}

