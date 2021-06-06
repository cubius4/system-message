package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.LogMessage;

/**
 * On this scene, the user can add a new log message to the system log or generate a random description for a machine ID and
 * add the new log message to the system log.
 */
public class AddPage extends VBox implements Page {
    /**
     * The scene library that this scene belongs to
     */
    private SceneLibrary S;

    /*
        GUI components
     */
    private Label space1;
    private HBox back;
    private Label space;
    private HBox add;
    private Label space2;
    private HBox random;
    private Label notif;

    private Button but;
    private TextField text;
    private Button a;
    private Label label;

    private Button generate;

    /**
     * Constructs a new AddPage object.
     * @param s The scene library that this scene belongs to
     */
    public AddPage(SceneLibrary s) {
        S = s;

        space1 = new Label(" ");
        space = new Label(" ");
        space2 = new Label(" ");

        add = new HBox();
        random = new HBox();
        notif = new Label("");
        back = new HBox();

        label = new Label("   ");

        but = new Button("<- Back");

        text = new TextField();
        a = new Button("Add");
        generate = new Button("Generate random description for this MACHINEID");

        work();
    }

    /**
     * Sets up the scene by placing all the GUI components in their correct positions and assigning them the correct
     * functions.
     */
    public void work() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        back.setAlignment(Pos.CENTER);
        add.setAlignment(Pos.CENTER);
        random.setAlignment(Pos.CENTER);

        space1.setFont(new Font("Times New Roman", 50));

        but.setOnAction(e -> {
            S.setMenuPage();
        });
        but.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        but.setFont(new Font("Consolas", 20));
        back.getChildren().add(but);

        notif.setFont(new Font("Consolas", 15));
        notif.setTextFill(Color.WHITE);

        a.setOnAction(e -> {
            if (!text.getText().contains(":")) {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: Valid LogMessage in the format MACHINEID:Description not found.");
            }
            else if(S.getGui().getLog().addMessage(new LogMessage(text.getText()))) {
                notif.setTextFill(Color.WHITE);
                notif.setText("LogMessage added successfully!");
            }
            else {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: System Log is full.");
            }
        });
        a.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        a.setFont(new Font("Consolas", 20));
        add.getChildren().addAll(text, label, a);

        generate.setOnAction(e -> {
            if (text.getText().contains(":")) {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: Valid MACHINEID not found.");
            }
            else if(S.getGui().getLog().isFull()) {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: System Log is full.");
            }
            else if(S.getGui().getLog().searchByMachineID(text.getText()).size() > 0 && text.getText().length() > 0) {
                String temp = S.getGui().getLog().generate();
                LogMessage message = new LogMessage(text.getText() + ":" + temp);
                S.getGui().getLog().addMessage(message);
                notif.setTextFill(Color.WHITE);
                notif.setText("LogMessage successfully generated: " + message.toString());
            }
            else {
                notif.setTextFill(Color.DARKRED);
                notif.setText("Error: System Log is empty or valid MACHINEID not found.");
            }
        });
        generate.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        generate.setFont(new Font("Consolas", 20));
        random.getChildren().add(generate);

        getChildren().addAll(space1, back, space, add, space2, random, notif);
    }
}
