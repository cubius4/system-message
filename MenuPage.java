package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The main menu scene
 */
public class MenuPage extends VBox implements Page{
    /*
        GUI components
     */
    private HBox back;
    private HBox view;
    private HBox add;
    private HBox remove;

    private Label space1;
    private Button b;
    private Label space2;
    private Button v;
    private Label space3;
    private Button a;
    private Label space4;
    private Button r;
    private Label space5;
    private Label notif;

    /**
     * The scene library that this scene belongs to
     */
    private SceneLibrary S;

    /**
     * Constructs a new MenuPage object.
     * @param s The scene library that this scene belongs to
     */
    public MenuPage(SceneLibrary s) {
        S = s;

        back = new HBox();
        view = new HBox();
        add = new HBox();
        remove = new HBox();

        space1 = new Label(" ");
        space2 = new Label(" ");
        space3 = new Label(" ");
        space4 = new Label(" ");
        space5 = new Label(" ");

        b = new Button("<- Back");
        v = new Button("View Messages");
        a = new Button("Add Message");
        r = new Button("Remove Message");

        notif = new Label(" ");

        work();
    }

    /**
     * Sets up the scene by placing all the GUI components in their correct positions and assigning them the correct
     * functions.
     */
    public void work() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        back.setAlignment(Pos.CENTER);
        view.setAlignment(Pos.CENTER);
        add.setAlignment(Pos.CENTER);
        remove.setAlignment(Pos.CENTER);

        space1.setFont(new Font("Times New Roman", 50));

        notif.setTextFill(Color.DARKRED);
        notif.setFont(new Font("Consolas", 15));

        b.setOnAction(e -> {
            S.setStartPage();
        });
        b.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        b.setFont(new Font("Consolas", 20));
        back.getChildren().add(b);

        space2.setFont(new Font("Times New Roman", 50));

        v.setOnAction(e -> {
            S.setViewPage();
        });
        v.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        v.setFont(new Font("Consolas", 20));
        view.getChildren().add(v);

        a.setOnAction(e -> {
            if (!S.getGui().getLog().isFull()) {
                notif.setText("");
                S.setAddPage();
            }
            else {
                notif.setText("Error: System log memory full. Clear messages to clear memory.");
            }
        });
        a.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        a.setFont(new Font("Consolas", 20));
        add.getChildren().add(a);

        r.setOnAction(e -> {
            S.setRemovePage();
        });
        r.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        r.setFont(new Font("Consolas", 20));
        remove.getChildren().add(r);

        getChildren().addAll(space1, back, space2, view, space3, add, space4, remove, space5, notif);
    }
}
