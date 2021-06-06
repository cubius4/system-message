package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.SystemLog;

/**
 * The main class of this program, containing the main method and also linking the front and back end of the program.
 */
public class GUI extends Application {
    /**
     * The scene library, where all scenes are stored
     */
    private SceneLibrary s;
    /**
     * The system log, where all log messages are stored
     */
    private SystemLog log;

    /**
     * Starts the program.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the back end and front end parts of the program, and loads up the GUI.
     * @param primaryStage The stage which the GUI is mounted on
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            s = new SceneLibrary(this);

            log = new SystemLog(this);

            s.setPrimaryStage(primaryStage);
            primaryStage.setTitle("SYSTEM MESSAGE");
            primaryStage.setResizable(false);

            s.setStartPage();
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Getter for the system log.
     * @return The system log
     */
    public SystemLog getLog() {return log;}
}
