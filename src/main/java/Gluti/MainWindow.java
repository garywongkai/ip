package Gluti;

import Gluti.Gui.DialogBox;
import Gluti.helpers.Ui;
import Gluti.utils.GlutiException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gluti gluti;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/data/usericon.jpg"));
    private Image gluticon = new Image(this.getClass().getResourceAsStream("/data/Gluticon.png"));

    @FXML
    public void initialize(Gluti gluti, Ui ui) {
        this.gluti = gluti;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getGlutiDialog(ui.typeHi(), gluticon));
    }
    public void setGluti(Gluti g) {
        gluti = g;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws GlutiException {
        String input = userInput.getText();
        String response = gluti.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGlutiDialog(response, gluticon)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}