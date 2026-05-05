import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("Welcome to the App!");

        // Description
        Label description = new Label("Please choose your gender to continue.");

        // Buttons
        Button maleButton = new Button("Male");
        Button femaleButton = new Button("Female");

        // Actions
        maleButton.setOnAction(e -> {
            System.out.println("Male Selected!");
        });

        femaleButton.setOnAction(e -> {
            System.out.println("Female Selected!");
        });

        // Horizontal layout (side by side)
        HBox buttonLayout = new HBox(10, maleButton, femaleButton);

        // Main layout
        VBox root = new VBox(15, title, description, buttonLayout);

        // Scene
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}