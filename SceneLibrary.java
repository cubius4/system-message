package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class stores all scenes and allows for quick switching between scenes.
 */
public class SceneLibrary {
    /**
     * The width of every scene
     */
    private static final int WIDTH = 600;
    /**
     * The height of every scene
     */
    private static final int HEIGHT = 500;
    /**
     * The GUI that this scene library belongs to
     */
    private GUI gui;
    /**
     * The stage that each scene is mounted on
     */
    private Stage primaryStage;

    /*
        Scene roots
     */
    private StartPage start;
    private MenuPage menu;
    private ViewMenuPage view;
    private AddPage add;
    private RemovePage remove;

    /*
        Scenes
     */
    private Scene startPage;
    private Scene menuPage;
    private Scene viewPage;
    private Scene addPage;
    private Scene removePage;

    /**
     * Constructs a new SceneLibrary object.
     * @param g The GUI that this scene library belongs to
     */
    public SceneLibrary(GUI g) {
        gui = g;

        start = new StartPage(this);
        menu = new MenuPage(this);
        view = new ViewMenuPage(this);
        add = new AddPage(this);
        remove = new RemovePage(this);

        startPage = new Scene(start, WIDTH, HEIGHT);
        menuPage = new Scene(menu, WIDTH, HEIGHT);
        viewPage = new Scene(view, WIDTH, HEIGHT);
        addPage = new Scene(add, WIDTH, HEIGHT);
        removePage = new Scene(remove, WIDTH, HEIGHT);
    }

    /**
     * Sets the primary stage.
     * @param stage The stage that will become the primary stage
     */
    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * Sets the start page as the current scene.
     */
    public void setStartPage() {
        primaryStage.setScene(startPage);
    }

    /**
     * Sets the menu page as the current scene.
     */
    public void setMenuPage() {
        primaryStage.setScene(menuPage);
    }

    /**
     * Sets the view page as the current scene.
     */
    public void setViewPage() {
        primaryStage.setScene(viewPage);
    }

    /**
     * Sets the add page as the current scene.
     */
    public void setAddPage() {
        primaryStage.setScene(addPage);
    }

    /**
     * Sets the remove page as the current scene.
     */
    public void setRemovePage() {
        primaryStage.setScene(removePage);
    }

    /**
     * Getter for the GUI.
     * @return the GUI
     */
    public GUI getGui() {return gui;}
}
