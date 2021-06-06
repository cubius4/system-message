package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.LogMessage;

import java.util.List;

/**
 * This scene allows the user to view all of the log messages, log messages with a certain machine ID, or log messages
 * with a certain keyword in their description.
 */
public class ViewMenuPage extends VBox implements Page {
    /**
     * The scene library that this scene belongs to
     */
    private SceneLibrary S;

    /*
        GUI components
     */
    private Button but;
    private Label space1;
    private HBox back;
    private Label space;
    private HBox viewAll;
    private Label space2;
    private HBox countBox;
    private Label space3;
    private HBox search;

    private Button view;
    private final ComboBox<String> searchOption;
    private TextField text;
    private Button searchBut;
    private Label[] labels;

    private Label l1;
    private Label l2;

    /**
     * Constructs a new ViewMenuPage object.
     * @param s The scene library that this scene belongs to
     */
    public ViewMenuPage(SceneLibrary s) {
        S = s;

        back = new HBox();
        viewAll = new HBox();
        countBox = new HBox();
        search = new HBox();

        space1 = new Label(" ");
        space = new Label(" ");
        space2 = new Label(" ");
        space3 = new Label(" ");

        but = new Button("<- Back");

        l1 = new Label("   ");
        l2 = new Label("   ");

        view = new Button("View All Messages");
        searchOption = new ComboBox<String>(FXCollections.observableArrayList(
                "Search By Machine ID",
                "Search By Description",
                "View Count"
        ));
        text = new TextField();
        searchBut = new Button("Search");
        labels = new Label[20];

        work();
    }

    /**
     * Sets up the scene by placing all the GUI components in their correct positions and assigning them the correct
     * functions.
     */
    public void work() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        back.setAlignment(Pos.CENTER);
        viewAll.setAlignment(Pos.CENTER);
        countBox.setAlignment(Pos.CENTER);
        search.setAlignment(Pos.CENTER);

        but.setOnAction(e -> {
            S.setMenuPage();
        });
        but.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        but.setFont(new Font("Consolas", 20));
        back.getChildren().add(but);

        space1.setFont(new Font("Times New Roman", 50));

        space3.setFont(new Font("Consolas", 15));
        space3.setTextFill(Color.DARKRED);

        view.setOnAction(e -> {
            resetLabels();
            editLabels(S.getGui().getLog().viewAll());
        });
        view.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        view.setFont(new Font("Consolas", 20));
        viewAll.getChildren().add(view);

        searchOption.setBackground(new Background(
                new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)
        ));

        searchBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                try {
                    if (searchOption.getValue().equals("Search By Machine ID") &&
                            !searchOption.getValue().isEmpty()) {
                        if (S.getGui().getLog().searchByMachineID(text.getText()).size() == 0) {
                            resetLabels();
                            space3.setText("Error: MACHINEID not found or the log is currently empty.");
                        } else {
                            resetLabels();
                            List<LogMessage> temp = S.getGui().getLog().searchByMachineID(text.getText());
                            editLabels(temp);
                        }
                    } else if (searchOption.getValue().equals("Search By Description") &&
                            !searchOption.getValue().isEmpty()) {
                        if (S.getGui().getLog().searchByDescription(text.getText()).size() == 0) {
                            resetLabels();
                            space3.setText("Error: Keyword not found in any description (Field is case-sensitive).");
                        } else {
                            resetLabels();
                            List<LogMessage> temp = S.getGui().getLog().searchByDescription(text.getText());
                            editLabels(temp);
                        }

                    } else if (searchOption.getValue().equals("View Count") &&
                            !searchOption.getValue().isEmpty()) {
                        resetLabels();
                        labels[0].setText("Count of descriptions for this MACHINEID: " + S.getGui().getLog().count());
                    } else {
                        resetLabels();
                        space3.setText("Error: Search method not selected.");
                    }
                }
                catch(NullPointerException e) {
                    resetLabels();
                    space3.setText("Error: Search method not selected.");
                }
            }
        });
        searchBut.setBackground(new Background(
                new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)
        ));
        searchBut.setFont(new Font("Consolas", 20));
        search.getChildren().addAll(searchOption, l1, text, l2, searchBut);

        getChildren().addAll(space1, back, space, viewAll, space2, search, space3);

        initLabels();
    }

    public void initLabels() {
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label(" ");
            labels[i].setTextFill(Color.WHITE);
            labels[i].setFont(new Font("Consolas", 12.5));
            getChildren().add(labels[i]);
        }
    }
    public void editLabels(List<LogMessage> list) {
        for(int i = 0; i < list.size(); i++) {
            labels[i].setText(list.get(i).toString());
        }
    }
    public void resetLabels() {
        space3.setText(" ");
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText("");
        }
    }

    public Label[] getLabels() {return labels;}
}
