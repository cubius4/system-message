package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This scene is the first scene that appears when the user starts the program. It is the title screen.
 */
public class StartPage extends VBox implements Page {
    /*
        GUI components
     */
    private HBox title;
    private HBox start;

    private Label space1;
    private Label label;
    private Button but;

    /**
     * The scene library that this scene belongs to
     */
    private SceneLibrary S;

    /**
     * Constructs a new StartPage object.
     * @param s The scene library that this scene belongs to
     */
    public StartPage(SceneLibrary s) {
        S = s;

        title = new HBox();
        start = new HBox();

        label = new Label("SYSTEM_MESSAGE");
        space1 = new Label(" ");
        but = new Button("Start");

        work();
    }

    /**
     * Sets up the scene by placing all the GUI components in their correct positions and assigning them the correct
     * functions.
     */
    public void work() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        title.setAlignment(Pos.CENTER);
        start.setAlignment(Pos.CENTER);

        space1.setFont(new Font("Times New Roman", 90));

        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Consolas", 70));

        but.setOnAction(e -> {
            S.setMenuPage();
        });
        but.setPrefSize(150, 112);
        but.setFont(new Font("Consolas", 25));
        but.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        title.getChildren().add(label);
        start.getChildren().add(but);
        getChildren().addAll(title, space1, start);
    }

}
