package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This scene allows the user to remove log messages by machine ID and by a keyword in the description
 */
public class RemovePage extends VBox implements Page {
    /**
     * The scene library that this scene belongs to
     */
    private SceneLibrary S;

    /*
        GUI components
     */
    private Label space1;
    private HBox back;
    private Label space2;
    private HBox field;
    private Label space3;
    private HBox buttons;
    private Label notif;

    private Button but;
    private TextField text;
    private Button rMessageID;
    private Label label;
    private Button rDesc;

    /**
     * Constructs a new RemovePage object.
     * @param s The scene library that this scene belongs to
     */
    public RemovePage(SceneLibrary s) {
        S = s;

        space1 = new Label(" ");
        back = new HBox();
        space2 = new Label(" ");
        field = new HBox();
        space3 = new Label(" ");
        buttons = new HBox();
        notif = new Label("");

        but = new Button("<- Back");
        text = new TextField();
        rMessageID = new Button("Remove by MACHINEID");
        label = new Label("   ");
        rDesc = new Button("Remove by Description");

        work();
    }

    /**
     * Sets up the scene by placing all the GUI components in their correct positions and assigning them the correct
     * functions.
     */
    public void work() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        back.setAlignment(Pos.CENTER);
        field.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        space1.setFont(new Font("Times New Roman", 50));

        notif.setFont(new Font("Consolas", 15));
        notif.setTextFill(Color.WHITE);

        but.setOnAction(e -> {
            S.setMenuPage();
        });
        but.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        but.setFont(new Font("Consolas", 20));
        back.getChildren().add(but);

        field.getChildren().add(text);

        rMessageID.setOnAction(e -> {
            if(text.getText().length() == 0) {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: No matching MACHINEIDs found.");
            }
            else if(S.getGui().getLog().removeByMachineID(text.getText()).size() > 0) {
                notif.setTextFill(Color.WHITE);
                notif.setText("Messages successfully removed!");
            }
            else {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: No matching MACHINEIDs found.");
            }
        });
        rMessageID.setBackground(new Background(
                new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        rMessageID.setFont(new Font("Consolas", 20));
        rDesc.setOnAction(e -> {
            if(text.getText().length() == 0) {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: No descriptions containing this keyword found.");
            }
            else if(S.getGui().getLog().removeByDescription(text.getText()).size() > 0) {
                notif.setTextFill(Color.WHITE);
                notif.setText("Messages successfully removed!");
            }
            else {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: No descriptions containing this keyword found.");
            }
        });
        rDesc.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        rDesc.setFont(new Font("Consolas", 20));
        buttons.getChildren().addAll(rMessageID, label, rDesc);

        getChildren().addAll(space1, back, space2, field, space3, buttons, notif);
    }
}
